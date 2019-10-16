package builder;

import com.onready.pdf.domain.Receipt;
import com.onready.pdf.domain.ReceiptBillItem;
import com.onready.pdf.domain.ReceiptPayItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ReceiptBuilder {

  private Receipt receipt;

  public ReceiptBuilder() {
    this.receipt = new Receipt();
  }

  public static ReceiptBuilder instanceOf() {
    return new ReceiptBuilder();
  }

  public ReceiptBuilder withCompany(String company) {
    receipt.setCompany(company);
    return this;
  }

  public ReceiptBuilder withCustomerId(String customerId) {
    receipt.setCustomerId(customerId);
    return this;
  }

  public ReceiptBuilder withBusinessName(String businessName) {
    receipt.setBusinessName(businessName);
    return this;
  }

  public ReceiptBuilder withCustomerEmail(String customerEmail) {
    receipt.setCustomerEmail(customerEmail);
    return this;
  }

  public ReceiptBuilder withCuit(String cuit) {
    receipt.setCuit(cuit);
    return this;
  }

  public ReceiptBuilder withAddress(String address) {
    receipt.setAddress(address);
    return this;
  }

  public ReceiptBuilder withState(String state) {
    receipt.setState(state);
    return this;
  }

  public ReceiptBuilder withCity(String city) {
    receipt.setCity(city);
    return this;
  }

  public ReceiptBuilder withSellerCode(String sellerCode) {
    receipt.setSellerCode(sellerCode);
    return this;
  }

  public ReceiptBuilder withVoucherDate(Date voucherDate) {
    receipt.setVoucherDate(voucherDate);
    return this;
  }

  public ReceiptBuilder withFormattedNumber(String formattedNumber) {
    receipt.setFormattedNumber(formattedNumber);
    return this;
  }

  public ReceiptBuilder withDiscountCode(String discountCode) {
    receipt.setDiscountCode(discountCode);
    return this;
  }

  public ReceiptBuilder withDiscount(BigDecimal discount) {
    receipt.setDiscount(discount);
    return this;
  }

  public ReceiptBuilder withSubTotal(BigDecimal subTotal) {
    receipt.setSubTotal(subTotal);
    return this;
  }

  public ReceiptBuilder withNetSubTotal(BigDecimal netSubTotal) {
    receipt.setNetSubTotal(netSubTotal);
    return this;
  }

  public ReceiptBuilder withCashTotal(BigDecimal cashTotal) {
    receipt.setCashTotal(cashTotal);
    return this;
  }

  public ReceiptBuilder withChequeTotal(BigDecimal chequeTotal) {
    receipt.setChequeTotal(chequeTotal);
    return this;
  }

  public ReceiptBuilder withRetentionTotal(BigDecimal retentionTotal) {
    receipt.setRetentionTotal(retentionTotal);
    return this;
  }

  public ReceiptBuilder withReceiptTotal(BigDecimal receiptTotal) {
    receipt.setReceiptTotal(receiptTotal);
    return this;
  }

  public ReceiptBuilder withReceiptBillItems(List<ReceiptBillItem> receiptBillItems) {
    receipt.setReceiptBillItems(receiptBillItems);
    return this;
  }

  public ReceiptBuilder withReceiptPayItems(List<ReceiptPayItem> receiptPayItems) {
    receipt.setReceiptPayItems(receiptPayItems);
    return this;
  }

  public Receipt build() {
    return receipt;
  }


}
