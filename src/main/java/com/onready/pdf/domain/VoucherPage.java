package com.onready.pdf.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VoucherPage {

  private Voucher voucher;
  private ItemVoucher[] items;
  private BigDecimal pageSubTotal;
  private BigDecimal previousPageSubTotal;

  public String getPageSubTotal() {
    return pageSubTotal.toPlainString();
  }

  public String getPreviousPageSubTotal() {
    return previousPageSubTotal.toPlainString();
  }

  public BigDecimal getRawPageSubTotal() {
    return pageSubTotal;
  }
}
