package com.onready.pdf.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReceiptPage {

  private List<ReceiptBillItem> billItems;
  private List<ReceiptPayItem> payItems;
  private Receipt receipt;
  private BigDecimal pageBillsSubTotal;
  private BigDecimal previousPageBillsSubTotal;
  private BigDecimal pagePaysSubTotal;
  private BigDecimal previousPagePaysSubTotal;
  private Boolean isLast;

}
