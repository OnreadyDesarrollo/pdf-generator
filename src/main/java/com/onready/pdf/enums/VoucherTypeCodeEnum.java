package com.onready.pdf.enums;

import com.onready.pdf.domain.Voucher;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum VoucherTypeCodeEnum {

  FCA("FC A", 1),
  NDA("ND A", 2),
  NCA("NC A", 3),
  FCB("FC B", 6),
  NDB("ND B", 7),
  NCB("NC B", 8),
  FCE("FC E", 19);

  private final String type;
  private final int code;

  public String getType() {
    return type;
  }

  public int getCode() {
    return code;
  }

  public static int getCodeByVoucherType(Voucher voucher) {
    String voucherType = voucher.getVoucherType() + " " + voucher.getVoucherLetter();
    for (VoucherTypeCodeEnum voucherTypeCodeEnum : values()) {
      if (voucherTypeCodeEnum.getType().equals(voucherType)) {
        return voucherTypeCodeEnum.getCode();
      }
    }
    return 1;
  }

}
