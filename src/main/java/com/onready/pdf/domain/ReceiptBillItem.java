package com.onready.pdf.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReceiptBillItem {

  private String billNumber;
  private String date;
  private BigDecimal amount;
  private BigDecimal imputed;

}
