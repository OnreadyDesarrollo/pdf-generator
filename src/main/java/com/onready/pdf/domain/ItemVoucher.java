package com.onready.pdf.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemVoucher {

  private Long quantity;
  private String articleId;
  private String articleDescription;
  private String dispatch;
  private BigDecimal unitPrice;
  private BigDecimal totalAmount;
}
