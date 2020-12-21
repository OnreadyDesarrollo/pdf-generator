package com.onready.pdf.enums;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public enum StorePickUpEnum {
  DEFAULT(0,  "Cromosol", "Av. Del Campo 1500  | C1427APO | Ciudad de Buenos Aires | Argentina."),
  ELEVEN(11,  "Cromosol", "Av. Del Campo 1500  | C1427APO | Ciudad de Buenos Aires | Argentina."),
  THIRTEEN(13,  "Cromosol", "Av. Del Campo 1500 | C1427APO | Ciudad de Buenos Aires | Argentina."),
  FOUR_TEEN(14,  "Cromosol", "Av. Del Campo 1500 | C1427APO | Ciudad de Buenos Aires | Argentina."),
  FIX_TEEN(15,  "Cromosol", "Av. Del Campo 1500 | C1427APO | Ciudad de Buenos Aires | Argentina."),
  EIGHT_TEEN(18,  "Cromosol", "Paysandú 2358 | C1416CDZ | Ciudad de Buenos Aires | Argentina."),
  TWENTY_ONE(21,  "Cromosol", "Roberto Hooke 3754 | B1667 |Grand Bourg - Bs As | Argentina"),
  TWENTY_TWO(22,  "Cromosol", "Av. Del Campo 1500 | C1427APO | Ciudad de Buenos Aires | Argentina."),
  TWENTY_FOUR(24,  "Cromosol", "Roberto Hooke 3754 | B1667 |Grand Bourg - Bs As | Argentina"),
  TWENTY_FIVE(25,  "Cromosol", "Camino Gral. Belgrano 2916 |1874 |Avellaneda - Bs. As. | Argentina."),
  EIGHTY_SEVEN(8887,  "Cromosol", "Camino Gral. Belgrano 2916 |1874 |Avellaneda – Bs. As. | Argentina."),
  EIGHTY_EIGHT(8888,  "Cromosol", "Av. Del Campo 1500 | C1427APO | Ciudad de Buenos Aires | Argentina."),
  EIGHTY_NINE(8889,  "Cromosol", "Roberto Hooke 3754 | B1667 |Grand Bourg - Bs As | Argentina");

  private final int code;
  private final String company;
  private final String address;

  public int getCode() {
    return code;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public static String getAbbreviationByCode(int code) {
    for (StorePickUpEnum storePickUpEnum : values()) {
      if (storePickUpEnum.getCode() == code) {
        return storePickUpEnum.getAddress();
      }
    }
    log.error("The storePickUp with code {} does not exist.", code);
    return DEFAULT.getAddress();
  }


}