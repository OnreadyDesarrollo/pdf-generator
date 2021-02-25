package com.onready.pdf.enums;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public enum CuitEnum {

  CRO("CRO", 30500728317L),
  BBA("BBA", 30711285314L),
  AUT("AUT", 30709760447L);

  private final String abbreviation;
  private final Long cuit;

  public String getAbbreviation() {
    return abbreviation;
  }

  public Long getCuit() {
    return cuit;
  }

  public static Long getCuitByAbbreviation(String abbreviation) {
    for (CuitEnum cuitEnum : values()) {
      if (cuitEnum.getAbbreviation().equals(abbreviation)) {
        return cuitEnum.getCuit();
      }
    }
    log.debug("La abreviación {} no existe", abbreviation);
    return null;
  }

}
