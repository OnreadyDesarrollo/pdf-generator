package com.onready.pdf.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReceiptPayItem {

  private String cuit;
  private String counterfoil;
  private String bank;
  private String date;
  private BigDecimal amount;
  private String type;

}
