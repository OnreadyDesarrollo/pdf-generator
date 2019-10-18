import builder.*;
import com.onready.pdf.PdfGenerator;
import com.onready.pdf.domain.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class PdfGeneratorTest {

  private final PdfGenerator pdfGenerator = new PdfGenerator();

  @Test
  public void getPdf_validVoucherCRO_returnsPdf() {

    ItemVoucher itemVoucher = ItemVoucherBuilder.instanceOf()
        .withItemQuantity(3L)
        .withItemArticleId("57/1I")
        .withItemArticleDescription("RETEN OPTICA VW GOL 91/95 E/ARTEB-CIBIE")
        .withItemUnitPrice(new BigDecimal(218.57))
        .withItemTotalAmount(new BigDecimal(655.71))
        .build();

    Voucher voucher = this.generateVoucher("CRO", 99, Collections.singletonList(itemVoucher));

    VoucherPage voucherPage = VoucherPageBuilder.instanceOf()
        .withVoucher(voucher)
        .withPageSubTotal(null)
        .withPreviousPageSubTotal(null)
        .withItemsVoucher(new ItemVoucher[]{itemVoucher})
        .build();

    byte[] pdf = pdfGenerator.getPdf(Collections.singletonList(voucherPage));

    assertThat(pdf).isNotEmpty();
  }

  @Test
  public void getPdf_validVoucherBBA_returnsPdf() {

    ItemVoucher itemVoucher = ItemVoucherBuilder.instanceOf()
        .withItemQuantity(3L)
        .withItemArticleId("57/1I")
        .withItemArticleDescription("RETEN OPTICA VW GOL 91/95 E/ARTEB-CIBIE")
        .withItemUnitPrice(new BigDecimal(218.57))
        .withItemTotalAmount(new BigDecimal(655.71))
        .build();

    Voucher voucher = this.generateVoucher("BBA", 76, Collections.singletonList(itemVoucher));

    VoucherPage voucherPage = VoucherPageBuilder.instanceOf()
        .withVoucher(voucher)
        .withPageSubTotal(null)
        .withPreviousPageSubTotal(null)
        .withItemsVoucher(new ItemVoucher[]{itemVoucher})
        .build();

    byte[] pdf = pdfGenerator.getPdf(Collections.singletonList(voucherPage));

    assertThat(pdf).isNotNull();
  }

  @Test
  public void getPdf_validVoucherAVE_returnsPdf() {

    ItemVoucher itemVoucher = ItemVoucherBuilder.instanceOf()
        .withItemQuantity(3L)
        .withItemArticleId("57/1I")
        .withItemArticleDescription("RETEN OPTICA VW GOL 91/95 E/ARTEB-CIBIE")
        .withItemUnitPrice(new BigDecimal(218.57))
        .withItemTotalAmount(new BigDecimal(655.71))
        .build();

    Voucher voucher = this.generateVoucher("BBA", 14, Collections.singletonList(itemVoucher));

    VoucherPage voucherPage = VoucherPageBuilder.instanceOf()
        .withVoucher(voucher)
        .withPageSubTotal(null)
        .withPreviousPageSubTotal(null)
        .withItemsVoucher(new ItemVoucher[]{itemVoucher})
        .build();

    byte[] pdf = pdfGenerator.getPdf(Collections.singletonList(voucherPage));

    assertThat(pdf).isNotNull();
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPdf_invalidVoucher_throwIllegalArgumentException() {

    ItemVoucher itemVoucher = ItemVoucherBuilder.instanceOf()
        .withItemQuantity(3L)
        .withItemArticleId("57/1I")
        .withItemArticleDescription("RETEN OPTICA VW GOL 91/95 E/ARTEB-CIBIE")
        .withItemUnitPrice(new BigDecimal(218.57))
        .withItemTotalAmount(new BigDecimal(655.71))
        .build();

    Voucher voucher = VoucherBuilder.instanceOf()
        .withVoucherDate(new Date())
        .build();

    VoucherPage voucherPage = VoucherPageBuilder.instanceOf()
        .withVoucher(voucher)
        .withPageSubTotal(null)
        .withPreviousPageSubTotal(null)
        .withItemsVoucher(new ItemVoucher[]{itemVoucher})
        .build();
    pdfGenerator.getPdf(Collections.singletonList(voucherPage));

  }

  @Test
  public void getPdf_validReceipt_returnsReceiptPdf() {
    ReceiptBillItem receiptBillItem = this.generateReceiptBillItem();
    ReceiptPayItem receiptPayItem = this.generateReceiptPayItem();

    ReceiptPage receiptPage = this.generateReceiptPages(Collections.singletonList(receiptPayItem),
        Collections.singletonList(receiptBillItem));

    byte[] pdf = pdfGenerator.getReceiptPdf(Collections.singletonList(receiptPage));

    assertThat(pdf).isNotEmpty();
  }

  private Voucher generateVoucher(String company, int sucursal, List<ItemVoucher> itemVoucherList) {
    return VoucherBuilder.instanceOf()
        .withCompany(company)
        .withSucursal(sucursal)
        .withCustomerId("000015")
        .withBusinessName("Carrin")
        .withCustomerEmail("carrin@cromosol.com")
        .withVatCondition("INSCRIPTO")
        .withVoucherType("NC")
        .withVoucherDate(new Date())
        .withVoucherNumber(97818)
        .withVoucherLetter("A")
        .withFormattedNumber("0015-00097818")
        .withElectronicBill("305007283170600112930793497040820190815")
        .withBarCode("(NÀ7LOÔN1ÂÞ_ìÜPÆê1ÞEÆ)")
        .withItemQuantity(3)
        .withVoucherTotal(new BigDecimal(542.56))
        .withVoucherCode("05")
        .withVoucherTypeAccountId(91)
        .withVoucherDueDate(new Date())
        .withCaieType("CAEA")
        .withCaie("28047947832569")
        .withCaieDueDate(new Date())
        .withSubTotal(new BigDecimal(330.00))
        .withDiscount(new BigDecimal(100.10))
        .withNetSubTotal(new BigDecimal(219.90))
        .withVatAmount(new BigDecimal(92.26))
        .withGrossIncomePerception(new BigDecimal(10.98))
        .withObservations("NOTA DE CREDITO AUTOMÁTICA PARA EL/LOS RECLAMO/S: 27246")
        .withItems(itemVoucherList)
        .build();
  }

  private ReceiptPage generateReceiptPages(List<ReceiptPayItem> receiptPayItems, List<ReceiptBillItem> receiptBillItems) {
    Receipt receipt = ReceiptBuilder.instanceOf()
        .withCompany("Cromosol")
        .withCustomerId("000015")
        .withBusinessName("CARRIN")
        .withCustomerEmail("mailcarrin@gmail.com")
        .withCuit("22-22222-22")
        .withAddress("calle 123")
        .withState("Buenos aires")
        .withCity("CABA")
        .withSellerCode("015")
        .withVoucherDate(new Date())
        .withFormattedNumber("0015-123123")
        .withDiscountCode("02")
        .withDiscount(new BigDecimal(123))
        .withSubTotal(new BigDecimal(1234))
        .withNetSubTotal(new BigDecimal(12345))
        .withCashTotal(new BigDecimal(12))
        .withChequeTotal(new BigDecimal(0))
        .withRetentionTotal(new BigDecimal(123))
        .withReceiptTotal(new BigDecimal(1000))
        .withReceiptPayItems(receiptPayItems)
        .withReceiptBillItems(receiptBillItems)
        .build();

    return ReceiptPageBuilder.instanceOf()
        .withIsLast(true)
        .withReceipt(receipt)
        .withBillItems(receipt.getReceiptBillItems())
        .withPayItems(receipt.getReceiptPayItems())
        .withPageBillsSubTotal(new BigDecimal(1000))
        .withPagePaysSubTotal(new BigDecimal(150))
        .build();
  }

  private ReceiptBillItem generateReceiptBillItem() {
    return ReceiptBillItemBuilder.instanceOf()
        .withBillNumber("123123")
        .withAmount(new BigDecimal(500))
        .withDate("2019-08-20")
        .withImputed(new BigDecimal(123))
        .build();
  }

  private ReceiptPayItem generateReceiptPayItem() {
    return ReceiptPayItemBuilder.instanceOf()
        .withBank("Rio")
        .withCounterfoil("counterfoil")
        .withCuit("22-2222-22")
        .withDate("2019-08-20")
        .withType("tipo")
        .withAmount(new BigDecimal(500))
        .build();
  }
}
