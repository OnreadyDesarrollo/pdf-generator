package com.onready.pdf;

import com.onready.pdf.domain.*;
import com.onready.pdf.exception.PDFCreationException;
import com.onready.pdf.utils.PdfCreationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
public class PdfGenerator {

  private final PdfCreationUtil pdfCreationUtil = new PdfCreationUtil();

  private static final String CROMOSOL_COMPANY = "CRO";
  private static final Long AVELLANEDA_BRANCH = 14L;
  private static final String XSL_EXTENSION = ".xsl";

  private static final Integer ITEM_PARTITION_VOUCHER_SIZE = 30;
  private static final Integer ITEM_PARTITION_RECEIPT_SIZE = 15;

  public byte[] getPdf(List<VoucherPage> voucherPages) {
    try {
      Voucher voucher = voucherPages.get(0).getVoucher();
      String voucherCompany = voucher.getCompany();
      boolean isAve = voucher.getSucursal() == AVELLANEDA_BRANCH
          && !voucherCompany.equals(CROMOSOL_COMPANY);
      StringBuilder templatePath = new StringBuilder("comprobantes/template-")
          .append(voucher.getVoucherType())
          .append("-")
          .append((isAve ? "AVE" : voucherCompany))
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
    List<ReceiptPage> receiptPages = new LinkedList();
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

}
