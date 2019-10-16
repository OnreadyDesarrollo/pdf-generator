package builder;

import com.onready.pdf.domain.ReceiptBillItem;
import com.onready.pdf.domain.ReceiptPage;
import com.onready.pdf.domain.ReceiptPayItem;

import java.math.BigDecimal;
import java.util.List;

public class ReceiptPageBuilder {

  private ReceiptPage receiptPage;

  public ReceiptPageBuilder() {
    this.receiptPage = new ReceiptPage();
  }

  public ReceiptPageBuilder withBillItems(List<ReceiptBillItem> billItems) {
    receiptPage.setBillItems(billItems);
    return this;
  }

  public ReceiptPageBuilder withPayItems(List<ReceiptPayItem> payItems) {
    receiptPage.setPayItems(payItems);
    return this;
  }

  public ReceiptPageBuilder withPageBillsSubTotal(BigDecimal pageBillsSubTotal) {
    receiptPage.setPageBillsSubTotal(pageBillsSubTotal);
    return this;
  }

  public ReceiptPageBuilder withPreviousPageBillsSubTotal(BigDecimal previousPageBillsSubTotal) {
    receiptPage.setPreviousPageBillsSubTotal(previousPageBillsSubTotal);
    return this;
  }

  public ReceiptPageBuilder withPagePaysSubTotal(BigDecimal pagePaysSubTotal) {
    receiptPage.setPagePaysSubTotal(pagePaysSubTotal);
    return this;
  }

  public ReceiptPageBuilder withPreviousPagePaysSubTotal(BigDecimal previousPagePaysSubTotal) {
    receiptPage.setPreviousPagePaysSubTotal(previousPagePaysSubTotal);
    return this;
  }

  public ReceiptPageBuilder withIsLast(Boolean isLast) {
    receiptPage.setIsLast(isLast);
    return this;
  }

  public ReceiptPage build() {
    return receiptPage;
  }


}
