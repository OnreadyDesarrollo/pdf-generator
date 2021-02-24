package com.onready.pdf.enums;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public enum CuitEnum {

  CRO("CRO", "30-50072831-7"),
  BBA("BBA", "30-71128531-4"),
  AUT("AUT", "30-70976044-7");

  private final String abbreviation;
  private final String cuit;

  public String getAbbreviation() {
    return abbreviation;
  }

  public static String getCuitByAbbreviation(String abbreviation) {
    for (CuitEnum cuitEnum : values()) {
      if (cuitEnum.getAbbreviation().equals(abbreviation)) {
        return cuitEnum.getAbbreviation();
      }
    }
    log.debug("La abreviación {} no existe", abbreviation);
    return null;
  }

}
