package com.onready.pdf.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Data
public class Voucher {

  private String company;
  private String customerId;
  private String businessName;
  private String customerEmail;
  private String vatCondition;
  private String voucherType;
  private Date voucherDate;
  private int sucursal;
  private int voucherNumber;
  private String voucherLetter;
  private String formattedNumber;
  private String electronicBill;
  private String barCode;
  private int itemQuantity;
  private BigDecimal voucherTotal;
  private String voucherCode;
  private int ctaCteTipoComprobanteID;
  private Date voucherDueDate;
  @JsonProperty("caietype")
  private String caieType;
  private String caie;
  @JsonProperty("caiedueDate")
  private Date caieDueDate;
  private BigDecimal subTotal;
  private BigDecimal discount;
  private BigDecimal netSubTotal;
  private BigDecimal vatAmount;
  private BigDecimal grossIncomePerception;
  private String includedOrders;
  private String expeditions;
  private String observations;
  private String jurisdiction;
  private String cuit;
  private String address;
  private String state;
  private String city;
  private String sellerCode;

  private String caieExpirationDate;
  private String companyAddress;
  private String qr;

  private List<ItemVoucher> items;

  public String getCaieExpirationDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    try {
      return formatter.format(this.getCaieDueDate());
    } catch (NullPointerException ignored) {
      log.info("El campo [VtoCAIE] está vacío");
      return "";
    }
  }

  public String getVoucherDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    return formatter.format(this.voucherDate);
  }

  public String getVoucherDateForQr() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(this.voucherDate);
  }

  public Date getUntransformedVoucherDate() {
    return this.voucherDate;
  }
}
