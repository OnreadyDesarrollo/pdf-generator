package com.onready.pdf.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Receipt {

  private String company;
  private String customerId;
  private String businessName;
  private String customerEmail;
  private String cuit;
  private String address;
  private String state;
  private String city;
  private String sellerCode;

  private Date voucherDate;
  private String formattedNumber;
  private String discountCode;
  private BigDecimal discount;
  private BigDecimal subTotal;
  private BigDecimal netSubTotal;
  private BigDecimal cashTotal;
  private BigDecimal chequeTotal;
  private BigDecimal retentionTotal;
  private BigDecimal receiptTotal;

  private List<ReceiptBillItem> receiptBillItems;
  private List<ReceiptPayItem> receiptPayItems;

}
