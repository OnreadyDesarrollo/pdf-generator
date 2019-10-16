package builder;

import com.onready.pdf.domain.ReceiptBillItem;

import java.math.BigDecimal;

public class ReceiptBillItemBuilder {

  private ReceiptBillItem receiptBillItem;

  public ReceiptBillItemBuilder() {
    this.receiptBillItem = new ReceiptBillItem();
  }

  public static ReceiptBillItemBuilder instanceOf() {
    return new ReceiptBillItemBuilder();
  }

  public ReceiptBillItemBuilder withBillNumber(String billNumber) {
    receiptBillItem.setBillNumber(billNumber);
    return this;
  }

  public ReceiptBillItemBuilder withDate(String date) {
    receiptBillItem.setDate(date);
    return this;
  }

  public ReceiptBillItemBuilder withAmount(BigDecimal amount) {
    receiptBillItem.setAmount(amount);
    return this;
  }

  public ReceiptBillItemBuilder withImputed(BigDecimal imputed) {
    receiptBillItem.setImputed(imputed);
    return this;
  }

  public ReceiptBillItem build() {
    return receiptBillItem;
  }
}