package builder;

import com.onready.pdf.domain.ReceiptPayItem;

import java.math.BigDecimal;

public class ReceiptPayItemBuilder {

  private ReceiptPayItem receiptPayItem;

  public ReceiptPayItemBuilder() {
    this.receiptPayItem = new ReceiptPayItem();
  }

  public static ReceiptPayItemBuilder instanceOf() {
    return new ReceiptPayItemBuilder();
  }

  public ReceiptPayItemBuilder withCuit(String cuit) {
    receiptPayItem.setCuit(cuit);
    return this;
  }

  public ReceiptPayItemBuilder withCounterfoil(String counterfoil) {
    receiptPayItem.setCounterfoil(counterfoil);
    return this;
  }

  public ReceiptPayItemBuilder withBank(String bank) {
    receiptPayItem.setBank(bank);
    return this;
  }

  public ReceiptPayItemBuilder withDate(String date) {
    receiptPayItem.setDate(date);
    return this;
  }

  public ReceiptPayItemBuilder withAmount(BigDecimal amount) {
    receiptPayItem.setAmount(amount);
    return this;
  }

  public ReceiptPayItemBuilder withType(String type) {
    receiptPayItem.setType(type);
    return this;
  }

  public ReceiptPayItem build() {
    return receiptPayItem;
  }

}