package builder;

import com.onready.pdf.domain.ItemVoucher;
import com.onready.pdf.domain.Voucher;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class VoucherBuilder {

  private Voucher voucher;

  private VoucherBuilder() {
    this.voucher = new Voucher();
  }

  public static VoucherBuilder instanceOf() {
    return new VoucherBuilder();
  }

  public VoucherBuilder withCompany(String company) {
    voucher.setCompany(company);
    return this;
  }

  public VoucherBuilder withCustomerId(String customerId) {
    voucher.setCustomerId(customerId);
    return this;
  }

  public VoucherBuilder withBusinessName(String businessName) {
    voucher.setBusinessName(businessName);
    return this;
  }

  public VoucherBuilder withCustomerEmail(String customerEmail) {
    voucher.setCustomerEmail(customerEmail);
    return this;
  }

  public VoucherBuilder withVatCondition(String vatCondition) {
    voucher.setVatCondition(vatCondition);
    return this;
  }

  public VoucherBuilder withVoucherType(String voucherType) {
    voucher.setVoucherType(voucherType);
    return this;
  }

  public VoucherBuilder withVoucherDate(Date voucherDate) {
    voucher.setVoucherDate(voucherDate);
    return this;
  }

  public VoucherBuilder withVoucherNumber(int voucherNumber) {
    voucher.setVoucherNumber(voucherNumber);
    return this;
  }

  public VoucherBuilder withVoucherLetter(String voucherLetter) {
    voucher.setVoucherLetter(voucherLetter);
    return this;
  }

  public VoucherBuilder withFormattedNumber(String formattedNumber) {
    voucher.setFormattedNumber(formattedNumber);
    return this;
  }

  public VoucherBuilder withElectronicBill(String electronicBill) {
    voucher.setElectronicBill(electronicBill);
    return this;
  }

  public VoucherBuilder withBarCode(String barCode) {
    voucher.setBarCode(barCode);
    return this;
  }

  public VoucherBuilder withItemQuantity(int itemQuantity) {
    voucher.setItemQuantity(itemQuantity);
    return this;
  }

  public VoucherBuilder withVoucherTotal(BigDecimal voucherTotal) {
    voucher.setVoucherTotal(voucherTotal);
    return this;
  }

  public VoucherBuilder withVoucherCode(String voucherCode) {
    voucher.setVoucherCode(voucherCode);
    return this;
  }

  public VoucherBuilder withVoucherTypeAccountId(int ctaCteTipoComprobanteId) {
    voucher.setCtaCteTipoComprobanteID(ctaCteTipoComprobanteId);
    return this;
  }

  public VoucherBuilder withVoucherDueDate(Date voucherDueDate) {
    voucher.setVoucherDate(voucherDueDate);
    return this;
  }

  public VoucherBuilder withCaieType(String caieType) {
    voucher.setCaieType(caieType);
    return this;
  }

  public VoucherBuilder withCaie(String caie) {
    voucher.setCaie(caie);
    return this;
  }

  public VoucherBuilder withCaieDueDate(Date caieDueDate) {
    voucher.setCaieDueDate(caieDueDate);
    return this;
  }

  public VoucherBuilder withSubTotal(BigDecimal subTotal) {
    voucher.setSubTotal(subTotal);
    return this;
  }

  public VoucherBuilder withDiscount(BigDecimal discount) {
    voucher.setDiscount(discount);
    return this;
  }

  public VoucherBuilder withNetSubTotal(BigDecimal netSubTotal) {
    voucher.setNetSubTotal(netSubTotal);
    return this;
  }

  public VoucherBuilder withVatAmount(BigDecimal vatAmount) {
    voucher.setVatAmount(vatAmount);
    return this;
  }

  public VoucherBuilder withGrossIncomePerception(BigDecimal grossIncomePerception) {
    voucher.setGrossIncomePerception(grossIncomePerception);
    return this;
  }

  public VoucherBuilder withIncludedOrders(String includedOrders) {
    voucher.setIncludedOrders(includedOrders);
    return this;
  }

  public VoucherBuilder withExpeditions(String expeditions) {
    voucher.setExpeditions(expeditions);
    return this;
  }

  public VoucherBuilder withObservations(String observations) {
    voucher.setObservations(observations);
    return this;
  }

  public VoucherBuilder withItems(List<ItemVoucher> items) {
    voucher.setItems(items);
    return this;
  }

  public VoucherBuilder withSucursal(int sucursal){
    voucher.setSucursal(sucursal);
    return this;
  }

  public Voucher build() {
    return voucher;
  }

}
