package com.onready.pdf.enums;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public enum CromosolAddressEnum {

  DEFAULT(0, "Av. Del Campo 1500  | C1427APO | Ciudad de Buenos Aires | Argentina"),
  ELEVEN(11, "Av. Del Campo 1500  | C1427APO | Ciudad de Buenos Aires | Argentina"),
  THIRTEEN(13, "Av. Del Campo 1500 | C1427APO | Ciudad de Buenos Aires | Argentina"),
  FOUR_TEEN(14, "Av. Del Campo 1500 | C1427APO | Ciudad de Buenos Aires | Argentina"),
  FIX_TEEN(15, "Av. Del Campo 1500 | C1427APO | Ciudad de Buenos Aires | Argentina"),
  EIGHT_TEEN(18, "Paysandú 2358 | C1416CDZ | Ciudad de Buenos Aires | Argentina"),
  TWENTY_ONE(21, "Roberto Hooke 3754 | B1667 | Grand Bourg - Bs As | Argentina"),
  TWENTY_TWO(22, "Av. Del Campo 1500 | C1427APO | Ciudad de Buenos Aires | Argentina"),
  TWENTY_FOUR(24, "Roberto Hooke 3754 | B1667 | Grand Bourg - Bs As | Argentina"),
  TWENTY_FIVE(25, "Camino Gral. Belgrano 2916 | 1874 | Avellaneda - Bs. As. | Argentina"),
  TWENTY_SIX(26, "Diego A. Maradona (Ex Mons. Bufano) 1335 | CP B1754BHQ | Villa Luzuriaga - Bs. As. | Argentina"),
  EIGHTY_SEVEN(8887, "Camino Gral. Belgrano 2916 | 1874 | Avellaneda – Bs. As. | Argentina"),
  EIGHTY_EIGHT(8888, "Av. Del Campo 1500 | C1427APO | Ciudad de Buenos Aires | Argentina"),
  EIGHTY_NINE(8889, "Roberto Hooke 3754 | B1667 | Grand Bourg - Bs As | Argentina");

  private final int code;
  private final String address;

  public int getCode() {
    return code;
  }


  public String getAddress() {
    return address;
  }

  public static String getAbbreviationByCode(int code) {
    for (CromosolAddressEnum cromosolAddressEnum : values()) {
      if (cromosolAddressEnum.getCode() == code) {
        return cromosolAddressEnum.getAddress();
      }
    }
    log.error("The storePickUp with code {} does not exist.", code);
    return DEFAULT.getAddress();
  }


}