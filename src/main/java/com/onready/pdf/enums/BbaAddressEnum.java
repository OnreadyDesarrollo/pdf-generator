package com.onready.pdf.enums;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public enum BbaAddressEnum {

  DEFAULT(0,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina"),
  ONE(1,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina"),
  TWO(2,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina"),
  THREE(3,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina"),
  FIVE(5,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina"),
  SIX(6,  "Cno. Gral. Belgrano 2916 Dpto: C | Avellaneda - Bs As | Argentina"),
  SEVEN(7,  "Roberto Hooke 3905 | B1667 | Tortuguitas - Bs As | Argentina"),
  EIGHT(8,  "Cno. Gral. Belgrano 2916 Dpto: C | Avellaneda - Bs As | Argentina"),
  NINE(9,  "Monseñor Bufano 1345 | CP B1754BHQ | Villa Luzuriaga - Bs. As. | Argentina"),
  TEN(10,  "Paysandú 2358 | C1416CDZ | Ciudad de Buenos Aires | Argentina"),
  ELEVEN(11,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina"),
  TWELVE(12,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina"),
  THIRTEEN(13,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina"),
  FOURTEEN(14,  "Cno. Gral. Belgrano 2916 Dpto: C | Avellaneda - Bs As | Argentina"),
  FIFTEEN(15,  "Cno. Gral. Belgrano 2916 Dpto: C | Avellaneda - Bs As | Argentina"),
  SIXTEEN(16,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina"),
  SEVENTEEN(17,  "Camino Gral. Belgrano 2916 | 1874 | Avellaneda - Bs. As. | Argentina"),
  EIGHTEEN(18,  "Monseñor Bufano 1345 | CP B1754BHQ | Villa Luzuriaga - Bs. As. | Argentina"),
  NINETEEN(19,  "Paysandú 2358 | C1416CDZ | Ciudad de Buenos Aires | Argentina"),
  TWENTY_ONE(21,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina"),
  TWENTY_TWO(22,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina"),
  TWENTY_THREE(23,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina"),
  TWENTY_FOUR(24,  "Cno. Gral. Belgrano 2916 Dpto: C | Avellaneda - Bs As | Argentina"),
  EIGHTY_EIGHT(8888,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina"),
  EIGHTY_NINE(9998,  "Roberto Hooke 3754 | B1667 | Tortuguitas - Bs As | Argentina");

  private final int code;
  private final String address;

  public int getCode() {
    return code;
  }

  public String getAddress() {
    return address;
  }

  public static String getAbbreviationByCode(int code) {
    for (BbaAddressEnum bbaAddressEnum : values()) {
      if (bbaAddressEnum.getCode() == code) {
        return bbaAddressEnum.getAddress();
      }
    }
    log.error("The storePickUp with code {} does not exist.", code);
    return DEFAULT.getAddress();
  }


}
