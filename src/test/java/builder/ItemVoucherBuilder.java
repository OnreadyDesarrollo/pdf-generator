package builder;

import com.onready.pdf.domain.ItemVoucher;

import java.math.BigDecimal;

public class ItemVoucherBuilder {

  private ItemVoucher itemVoucher;

  private ItemVoucherBuilder() {
    this.itemVoucher = new ItemVoucher();
  }

  public static ItemVoucherBuilder instanceOf() {
    return new ItemVoucherBuilder();
  }

  public ItemVoucherBuilder withItemQuantity(Long itemQuantity) {
    itemVoucher.setQuantity(itemQuantity);
    return this;
  }

  public ItemVoucherBuilder withItemArticleId(String itemArticleId) {
    itemVoucher.setArticleId(itemArticleId);
    return this;
  }

  public ItemVoucherBuilder withItemArticleDescription(String itemArticleDescription) {
    itemVoucher.setArticleDescription(itemArticleDescription);
    return this;
  }

  public ItemVoucherBuilder withItemDispatch(String itemDispatch) {
    itemVoucher.setDispatch(itemDispatch);
    return this;
  }

  public ItemVoucherBuilder withItemUnitPrice(BigDecimal itemUnitPrice) {
    itemVoucher.setUnitPrice(itemUnitPrice);
    return this;
  }

  public ItemVoucherBuilder withItemTotalAmount(BigDecimal itemTotalAmount) {
    itemVoucher.setTotalAmount(itemTotalAmount);
    return this;
  }

  public ItemVoucher build() {
    return itemVoucher;
  }
}
