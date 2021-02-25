package com.onready.pdf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.onready.pdf.domain.*;
import com.onready.pdf.enums.CuitEnum;
import com.onready.pdf.enums.StorePickUpEnum;
import com.onready.pdf.enums.VoucherTypeCodeEnum;
import com.onready.pdf.exception.PDFCreationException;
import com.onready.pdf.utils.PdfCreationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
public class PdfGenerator {

  private final PdfCreationUtil pdfCreationUtil = new PdfCreationUtil();
  private final ObjectMapper objectMapper = new ObjectMapper();

  private static final String CROMOSOL_COMPANY = "CRO";
  private static final Long AVELLANEDA_BRANCH = 14L;
  private static final String XSL_EXTENSION = ".xsl";
  private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  private static final String AUTOVENTURE_START_DATE = "2020-07-07";
  private static final String QR_AFIP_URI = "https://www.afip.gob.ar/fe/qr/?p=";

  private static final Integer ITEM_PARTITION_VOUCHER_SIZE = 30;
  private static final Integer ITEM_PARTITION_RECEIPT_SIZE = 15;

  public byte[] getPdf(List<VoucherPage> voucherPages) {
    try {
      Voucher voucher = voucherPages.get(0).getVoucher();
      this.generateCompanyAddress(voucherPages);
      this.generateJsonAndQRForVoucher(voucher);
      StringBuilder templatePath = new StringBuilder("comprobantes/template-")
          .append(voucher.getVoucherType())
          .append("-")
          .append(this.getCompanyPath(voucher))
          .append(XSL_EXTENSION);
      log.debug("templatePath: " + templatePath);
      return pdfCreationUtil.generateFile(this.getClass()
          .getClassLoader()
          .getResourceAsStream(templatePath.toString()), voucherPages);
    } catch (PDFCreationException | NullPointerException e) {
      String message = "VoucherPages is not valid. Error: " + e.getMessage();
      log.error(message);
      throw new IllegalArgumentException(message);
    }
  }

  public static List<VoucherPage> paginateVoucher(Voucher voucher) {
    List<VoucherPage> voucherPages = new LinkedList<>();
    List<List<ItemVoucher>> itemPartitions = ListUtils.partition(voucher.getItems(), ITEM_PARTITION_VOUCHER_SIZE);
    for (int i = 0; i < itemPartitions.size(); i++) {
      List<ItemVoucher> partition = itemPartitions.get(i);
      VoucherPage voucherPage = new VoucherPage();
      voucherPage.setVoucher(voucher);
      voucherPage.setItems(partition.toArray(new ItemVoucher[0]));
      if (i < itemPartitions.size() - 1) {
        BigDecimal pageSubTotal;
        if (i == 0) {
          pageSubTotal = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        } else {
          pageSubTotal = voucherPages.get(i - 1).getPageSubTotal();
        }
        for (ItemVoucher itemVoucher : partition) {
          pageSubTotal = pageSubTotal.add(
              itemVoucher.getTotalAmount().setScale(2, RoundingMode.HALF_UP));
        }
        voucherPage.setPageSubTotal(pageSubTotal);
      }
      if (i > 0) {
        voucherPage.setPreviousPageSubTotal(voucherPages.get(i - 1).getPageSubTotal());
      }
      voucherPages.add(voucherPage);
    }
    return voucherPages;
  }

  public byte[] getReceiptPdf(List<ReceiptPage> receiptPages) {
    try {
      String templatePath = new StringBuilder("comprobantes/")
          .append("template-RC")
          .append(XSL_EXTENSION).toString();
      return pdfCreationUtil.generateFile(this.getClass()
          .getClassLoader()
          .getResourceAsStream(templatePath), receiptPages);
    } catch (PDFCreationException | NullPointerException e) {
      String message = "ReceiptPages is not valid. Error: " + e.getMessage();
      log.error(message);
      throw new IllegalArgumentException(message);
    }
  }

  public static List<ReceiptPage> paginateReceipt(Receipt receipt) {
    List<ReceiptPage> receiptPages = new LinkedList<>();
    int longPartitions;
    List<List<ReceiptBillItem>> billPartitions = ListUtils.partition(
        receipt.getReceiptVoucherBillItems(), ITEM_PARTITION_RECEIPT_SIZE);
    List<List<ReceiptPayItem>> payPartitions = ListUtils.partition(
        receipt.getReceiptVoucherPayItems(), ITEM_PARTITION_RECEIPT_SIZE);
    if (receipt.getReceiptVoucherBillItems().size() >= receipt.getReceiptVoucherPayItems().size()) {
      longPartitions = billPartitions.size();
    } else {
      longPartitions = payPartitions.size();
    }
    IntStream.range(0, longPartitions).forEach(index -> {
      ReceiptPage receiptPage = new ReceiptPage();
      receiptPage.setReceipt(receipt);
      receiptPage.setBillItems(billPartitions.size() >= index + 1 ? billPartitions.get(index) : new ArrayList<>());
      receiptPage.setPayItems(payPartitions.size() >= index + 1 ? payPartitions.get(index) : new ArrayList<>());
      receiptPage.setIsLast(true);
      if (index < billPartitions.size() - 1) {
        AtomicReference<BigDecimal> pageBillsSubTotal = new AtomicReference<>();
        if (index == 0) {
          pageBillsSubTotal.set(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        } else {
          pageBillsSubTotal.set(receiptPages.get(index - 1).getPageBillsSubTotal());
        }
        billPartitions.get(index).forEach(receiptBillItem -> {
          pageBillsSubTotal.set(pageBillsSubTotal.get().add(receiptBillItem.getImputed()).setScale(
              2, RoundingMode.HALF_UP));
        });
        receiptPage.setPageBillsSubTotal(pageBillsSubTotal.get());
        receiptPage.setIsLast(false);
      }
      if (index < payPartitions.size() - 1) {
        AtomicReference<BigDecimal> pagePaysSubTotal = new AtomicReference<>();
        if (index == 0) {
          pagePaysSubTotal.set(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        } else {
          pagePaysSubTotal.set(receiptPages.get(index - 1).getPagePaysSubTotal());
        }
        payPartitions.get(index).forEach(receiptPayItem -> {
          pagePaysSubTotal.set(pagePaysSubTotal.get().add(receiptPayItem.getAmount()).setScale(
              2, RoundingMode.HALF_UP));
        });
        receiptPage.setPagePaysSubTotal(pagePaysSubTotal.get());
        receiptPage.setIsLast(false);
      }
      if (index > 0) {
        receiptPage.setPreviousPageBillsSubTotal(receiptPages.get(index - 1).getPageBillsSubTotal());
        receiptPage.setPreviousPagePaysSubTotal(receiptPages.get(index - 1).getPagePaysSubTotal());
      }
      receiptPages.add(receiptPage);
    });
    return receiptPages;
  }

  private Date getAutoventureStartDate() {
    try {
      return FORMAT.parse(AUTOVENTURE_START_DATE);
    } catch (ParseException e) {
      throw new IllegalArgumentException("No se pudo parsear la fecha de Autoventure: " + e.getMessage());
    }
  }

  private boolean isAve(Voucher voucher) {
    return voucher.getSucursal() == AVELLANEDA_BRANCH && !voucher.getCompany().equals(CROMOSOL_COMPANY);
  }

  private boolean isAutoventure(Voucher voucher) {
    Date voucherDate = voucher.getUntransformedVoucherDate();
    Date autoventureDate = this.getAutoventureStartDate();
    return voucher.getCompany().equals("AUT") && voucherDate.after(autoventureDate);
  }

  private String getCompanyPath(Voucher voucher) {
    if (this.isAutoventure(voucher)) {
      return "AUT";
    } else if (this.isAve(voucher)) {
      return "AVE";
    } else {
      return voucher.getCompany();
    }
  }

  private void generateCompanyAddress(List<VoucherPage> voucherPages) {
    voucherPages.forEach(voucherPage ->
        voucherPage.getVoucher().setCompanyAddress(StorePickUpEnum
            .getAbbreviationByCode(voucherPage.getVoucher().getSucursal())));
  }

  private void generateJsonAndQRForVoucher(Voucher voucher) {
    QRJson qrJson = new QRJson();
    qrJson.setFecha(voucher.getVoucherDateForQr());
    qrJson.setCuit(Long.valueOf(CuitEnum.getCuitByAbbreviation(voucher.getCompany())));
    qrJson.setPtoVta(voucher.getSucursal());
    qrJson.setTipoCmp(VoucherTypeCodeEnum.getCodeByVoucherType(voucher));
    qrJson.setNroCmp(voucher.getVoucherNumber());
    qrJson.setImporte(voucher.getVoucherTotal());
    qrJson.setNroDocRec(Long.valueOf(voucher.getCuit().replace("-", "")));
    qrJson.setTipoCodAut(this.generateAuthorizationTypeCode(voucher));
    qrJson.setCodAut(Long.valueOf(voucher.getCaie()));
    try {
      String jsonText = objectMapper.writeValueAsString(qrJson);
      byte[] qrSource = this.writeQR(QR_AFIP_URI + Base64.getEncoder().encodeToString(jsonText.getBytes()));
      voucher.setQr("data:image/png;base64," + Base64.getEncoder().encodeToString(qrSource));
    } catch (WriterException | IOException e) {
      log.debug("[generateJsonAndQR] Hubo un error al generar el QR");
    }
  }

  private String generateAuthorizationTypeCode(Voucher voucher) {
    if (voucher.getCompany().equals("BBA") ||
        voucher.getCompany().equals("AUT") ||
        (voucher.getCompany().equals("CRO") && voucher.getVoucherLetter().equals("E"))) {
      return "E";
    } else {
      return "A";
    }
  }

  private byte[] writeQR(String text) throws IOException, WriterException {
    int width = 110;
    int height = 110;
    String imageFormat = "png";
    BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
  }
}
