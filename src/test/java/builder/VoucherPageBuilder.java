package builder;

import com.onready.pdf.domain.ItemVoucher;
import com.onready.pdf.domain.Voucher;
import com.onready.pdf.domain.VoucherPage;

import java.math.BigDecimal;

public class VoucherPageBuilder {

  private VoucherPage voucherPage;

  private VoucherPageBuilder() {
    this.voucherPage = new VoucherPage();
  }

  public VoucherPageBuilder withVoucher(Voucher voucher){
    voucherPage.setVoucher(voucher);
    return this;
  }

  public VoucherPageBuilder withPreviousPageSubTotal(BigDecimal previousPageSubTotal){
    voucherPage.setPreviousPageSubTotal(previousPageSubTotal);
    return this;
  }

  public VoucherPageBuilder withPageSubTotal(BigDecimal pageSubTotal){
    voucherPage.setPageSubTotal(pageSubTotal);
    return this;
  }

  public VoucherPageBuilder withItemsVoucher(ItemVoucher[] items){
    voucherPage.setItems(items);
    return this;
  }

  public static VoucherPageBuilder instanceOf() {
    return new VoucherPageBuilder();
  }

  public VoucherPage build() {
    return voucherPage;
  }

}
