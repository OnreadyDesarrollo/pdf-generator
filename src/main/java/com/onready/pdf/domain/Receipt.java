package com.onready.pdf.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
  private int sucursal;
  private int voucherNumber;
  private String voucherLetter;
  private String barCode;
  private int itemQuantity;
  private BigDecimal voucherTotal;
  private String voucherCode;
  private int ctaCteTipoComprobanteID;
  private Date voucherDueDate;
  private BigDecimal vatAmount;
  private BigDecimal grossIncomePerception;
  private String includedOrders;
  private String expeditions;
  private String observations;
  private String electronicBill;
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
  @JsonProperty("caietype")
  private String caieType;
  private String caie;
  @JsonProperty("caiedueDate")
  private Date caieDueDate;

  private List<ReceiptBillItem> receiptVoucherBillItems;
  private List<ReceiptPayItem> receiptVoucherPayItems;

}
