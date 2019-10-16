package com.onready.pdf;

import com.onready.pdf.domain.ReceiptPage;
import com.onready.pdf.domain.Voucher;
import com.onready.pdf.domain.VoucherPage;
import com.onready.pdf.exception.PDFCreationException;
import com.onready.pdf.utils.PdfCreationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PdfGenerator {

  private final PdfCreationUtil pdfCreationUtil = new PdfCreationUtil();

  private static final String CROMOSOL_COMPANY = "CRO";
  private static final Long AVELLANEDA_BRANCH = 14L;
  private static final String XSL_EXTENSION = ".xsl";

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
      return pdfCreationUtil.generateFile(this.getClass()
          .getClassLoader()
          .getResourceAsStream(templatePath.toString()), voucherPages);
    } catch (PDFCreationException | NullPointerException e) {
      String message = "VoucherPages is not valid" + e.getMessage();
      log.error(message);
      throw new IllegalArgumentException(message);
    }
  }

  public byte[] getReceiptPdf(List<ReceiptPage> receiptPages) {
    try {
      String templatePath = new StringBuilder("static")
          .append(File.separator).append("comprobantes")
          .append(File.separator).append("template-RC")
          .append(XSL_EXTENSION).toString();
      return pdfCreationUtil.generateFile(this.getClass()
          .getClassLoader()
          .getResourceAsStream(templatePath), receiptPages);
    } catch (PDFCreationException | NullPointerException e) {
      String message = "ReceiptPages is not valid" + e.getMessage();
      log.error(message);
      throw new IllegalArgumentException(message);
    }
  }

}
