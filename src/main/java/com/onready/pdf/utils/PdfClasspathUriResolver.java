package com.onready.pdf.utils;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Source;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

@Slf4j
public class PdfClasspathUriResolver implements URIResolver {

  private static final Logger LOGGER = LoggerFactory.getLogger(PdfClasspathUriResolver.class);

  @Override
  public Source resolve(String href, String base) {
    try {
      InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("comprobantes/" + href);
      return new StreamSource(inputStream);
    } catch (Exception ex) {
      LOGGER.error("Error resolviendo path del pdf", ex);
      return null;
    }
  }
}
