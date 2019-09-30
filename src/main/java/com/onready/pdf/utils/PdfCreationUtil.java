package com.onready.pdf.utils;

import com.onready.pdf.exception.PDFCreationException;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.fop.apps.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class PdfCreationUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(PdfCreationUtil.class);

  private String apacheFopConfigFilePath = "comprobantes/fop-config-dev.xml";

  public byte[] generateFile(InputStream xslTemplateFileInputStream, Object objectToRender)
      throws PDFCreationException {
    try {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      Fop fop = configureFop(outputStream);
      Transformer transformer = getTransformerFromXsl(xslTemplateFileInputStream);
      String xmlObject = new XmlUtil().getXmlFromObject(objectToRender);
      applyTransformation(xmlObject, fop, transformer);
      return outputStream.toByteArray();
    } catch (TransformerException | IOException | SAXException | ConfigurationException exception) {
      LOGGER.error("Error setting up FOP.", exception);
      throw new PDFCreationException(exception);
    }
  }

  private Fop configureFop(ByteArrayOutputStream outputStream) throws SAXException, IOException,
      ConfigurationException {
    FopFactoryBuilder fopFactoryBuilder = new FopFactoryBuilder(new File(".").toURI());
    fopFactoryBuilder.ignoreNamespace("http://www.w3.org/2001/XMLSchema-instance");
    DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
    InputStream configFile = this.getClass().getClassLoader().getResourceAsStream(apacheFopConfigFilePath);
    Configuration cfg = cfgBuilder.build(configFile);
    fopFactoryBuilder.setConfiguration(cfg);
    final FopFactory fopFactory = fopFactoryBuilder.build();
    FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
    return fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, outputStream);
  }

  private void applyTransformation(String xmlObject, Fop fop, Transformer transformer) throws TransformerException,
      FOPException {
    Source src = new StreamSource(new StringReader(xmlObject));
    Result res = new SAXResult(fop.getDefaultHandler());
    transformer.transform(src, res);
  }

  private Transformer getTransformerFromXsl(InputStream xslTemplateFileInputStream)
      throws TransformerConfigurationException, TransformerFactoryConfigurationError {
    TransformerFactory factory = TransformerFactory.newInstance();
    factory.setURIResolver(new PdfClasspathUriResolver());
    return factory.newTransformer(new StreamSource(xslTemplateFileInputStream));
  }

}
