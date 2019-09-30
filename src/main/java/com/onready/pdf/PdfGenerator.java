package com.onready.pdf;

import com.onready.pdf.domain.Voucher;
import com.onready.pdf.domain.VoucherPage;
import com.onready.pdf.exception.PDFCreationException;
import com.onready.pdf.utils.PdfCreationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PdfGenerator {

  private final PdfCreationUtil pdfCreationUtil = new PdfCreationUtil();

  private static final String CROMOSOL_COMPANY = "CRO";
  private static final Long AVELLANEDA_BRANCH = 14L;
  private static final String XSL_EXTENSION = ".xsl";

  public String getPdf(List<VoucherPage> voucherPages) {
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
      byte[] content = pdfCreationUtil.generateFile(this.getClass()
          .getClassLoader()
          .getResourceAsStream(templatePath.toString()), voucherPages);
      return Base64.getEncoder().encodeToString(content);
    } catch (PDFCreationException | NullPointerException e) {
      String message = "VoucherPages is not valid" + e.getMessage();
      log.error(message);
      throw new IllegalArgumentException(message);
    }
  }

}
