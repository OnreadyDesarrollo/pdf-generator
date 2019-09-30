package com.onready.pdf.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class XmlUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtil.class);

  /**
   * Adapts the object received to a valid xml String.
   *
   * @param objectToTransform object to be transformed to xml.
   * @return @{String} containing the xml.
   */
  String getXmlFromObject(Object objectToTransform) {
    DocumentWrapper documentWrapper = new DocumentWrapper(objectToTransform);
    XmlMapper xmlMapper = new XmlMapper();
    try {
      String xmlObject = xmlMapper.writeValueAsString(documentWrapper);
      LOGGER.debug(xmlObject);
      return xmlObject;
    } catch (JsonProcessingException exception) {
      LOGGER.error("Error procesando xml", exception);
    }
    return null;
  }

}
