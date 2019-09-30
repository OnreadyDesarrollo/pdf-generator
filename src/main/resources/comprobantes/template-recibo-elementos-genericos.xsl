<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:decimal-format name="decimalFormat" decimal-separator="," grouping-separator="."/>
    <xsl:variable name="smallcase" select="'abcdefghijklmnñopqrstuvwxyz'"/>
    <xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNÑOPQRSTUVWXYZ'"/>

    <xsl:template name="recibo">
        <xsl:param name="copy"/>
        <fo:block>
            <fo:block>
                <xsl:call-template name="cabecera">
                    <xsl:with-param name="copy" select='$copy'/>
                </xsl:call-template>
            </fo:block>
            <fo:block>
                <xsl:call-template name="datosCliente"/>
            </fo:block>
            <fo:block>
                <xsl:call-template name="cuerpoRecibo"/>
            </fo:block>
            <fo:block>
                <xsl:call-template name="seccionTotales"/>
            </fo:block>
            <fo:block>
                <xsl:call-template name="seccionNumeroPagina"/>
            </fo:block>
        </fo:block>
    </xsl:template>

    <xsl:template name="cabecera">
        <xsl:param name="copy"/>
        <fo:table table-layout="fixed" width="100%" border="none">
            <fo:table-column column-width="34%"/>
            <fo:table-column column-width="33%"/>
            <fo:table-column column-width="33%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell padding="6px 0px 1px 0px" font-size="13pt">
                        <fo:block>
                            <xsl:value-of select="voucher/companyName"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="6px 0px 1px 0px" font-size="10pt" font-weight="bold" text-align="center">
                        <fo:block>RECIBO</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="6px 0px 1px 0px" font-size="9pt" font-weight="bold" text-align="center">
                        <fo:block>
                            <xsl:value-of select="voucher/formattedNumber"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell font-size="8pt">
                        <fo:block></fo:block>
                    </fo:table-cell>
                    <fo:table-cell font-size="8pt" text-align="center">
                        <fo:block>
                            <xsl:value-of select="$copy"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell font-size="8pt" text-align="center">
                        <fo:block>
                            <xsl:value-of select="voucher/voucherDate"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row margin="6px 0px 6px 0px">
                    <fo:table-cell font-size="8pt">
                        <fo:block>
                            CUIT:
                            <xsl:value-of select="voucher/cuit"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="datosCliente">
        <fo:table table-layout="fixed" width="75%" border="none" font-size="9pt">
            <fo:table-column column-width="55%"/>
            <fo:table-column column-width="45%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell padding="10px 0px 0px 0px">
                        <fo:block>
                            <xsl:value-of select="customer/businessName"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="10px 0px 0px 0px">
                        <fo:block>
                            <xsl:value-of select="voucher/customerId"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:value-of select="voucher/address"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:value-of select="voucher/sellerCode"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:value-of select="voucher/city"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>&#160;</fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:value-of select="voucher/state"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:value-of select="voucher/cuit"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="cuerpoRecibo">
        <fo:table table-layout="fixed" width="100%" border="none">
            <fo:table-column column-width="40%"/>
            <fo:table-column column-width="2%"/>
            <fo:table-column column-width="58%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell padding="10px 0px 0px 0px">
                        <fo:block>
                            <xsl:call-template name="cuerpoReciboIzquierda"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="10px 0px 0px 0px">
                        <fo:block>&#160;</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="10px 0px 0px 0px">
                        <fo:block>
                            <xsl:call-template name="cuerpoReciboDerecha"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="cuerpoReciboIzquierda">
        <fo:table table-layout="fixed" width="100%" border="solid 0.3mm black" font-size="8pt">
            <fo:table-column column-width="30%"/>
            <fo:table-column column-width="20%"/>
            <fo:table-column column-width="25%"/>
            <fo:table-column column-width="25%"/>
            <fo:table-body>
                <fo:table-row font-style="italic" text-align="center">
                    <fo:table-cell padding="3px 0px 3px 0px" border="solid 0.3mm black">
                        <fo:block>Factura Nº</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="3px 0px 3px 0px" border="solid 0.3mm black">
                        <fo:block>Fecha</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="3px 0px 3px 0px" border="solid 0.3mm black">
                        <fo:block>Importe</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="3px 0px 3px 0px" border="solid 0.3mm black">
                        <fo:block>Imputado</fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <xsl:choose>
                    <xsl:when test="previousPageBillsSubTotal > 0">
                        <fo:table-row>
                            <fo:table-cell padding="1px 0px 1px 1px" border-right="solid 0.3mm black">
                                <fo:block>Arrastre</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 0px 1px 0px" border-right="solid 0.3mm black"
                                           text-align="center">
                                <fo:block></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 1px 1px 0px" border-right="solid 0.3mm black"
                                           text-align="right">
                                <fo:block></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 1px 1px 0px" text-align="right">
                                <fo:block>
                                    <xsl:value-of
                                            select="format-number(previousPageBillsSubTotal, '###.###.##0,00', 'decimalFormat')"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:when>
                </xsl:choose>
                <xsl:for-each select="billItems">
                    <fo:table-row>
                        <fo:table-cell padding="1px 0px 1px 2px" border-right="solid 0.3mm black">
                            <fo:block>
                                <xsl:value-of select="billNumber"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="1px 0px 1px 0px" border-right="solid 0.3mm black" text-align="center">
                            <fo:block>
                                <xsl:value-of select="date"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="1px 2px 1px 0px" border-right="solid 0.3mm black" text-align="right">
                            <xsl:choose>
                                <xsl:when test="amount >= 0">
                                    <fo:block>
                                        <xsl:value-of
                                                select="format-number(amount, '###.###.##0,00', 'decimalFormat')"/>
                                    </fo:block>
                                </xsl:when>
                                <xsl:otherwise>
                                    <fo:block>
                                        <xsl:value-of
                                                select="format-number(amount * -1, '(###.###.##0,00)', 'decimalFormat')"/>
                                    </fo:block>
                                </xsl:otherwise>
                            </xsl:choose>
                        </fo:table-cell>
                        <fo:table-cell padding="1px 2px 1px 0px" text-align="right">
                            <xsl:choose>
                                <xsl:when test="imputed >= 0">
                                    <fo:block>
                                        <xsl:value-of
                                                select="format-number(imputed, '###.###.##0,00', 'decimalFormat')"/>
                                    </fo:block>
                                </xsl:when>
                                <xsl:otherwise>
                                    <fo:block>
                                        <xsl:value-of
                                                select="format-number(imputed * -1, '(###.###.##0,00)', 'decimalFormat')"/>
                                    </fo:block>
                                </xsl:otherwise>
                            </xsl:choose>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:for-each>
            </fo:table-body>
        </fo:table>
        <fo:table table-layout="fixed" width="100%" border-left="solid 0.3mm black" border-right="solid 0.3mm black"
                  border-bottom="solid 0.3mm black" font-size="8pt">
            <fo:table-column column-width="33%"/>
            <fo:table-column column-width="34%"/>
            <fo:table-column column-width="33%"/>
            <fo:table-body>
                <xsl:choose>
                    <xsl:when test="pageBillsSubTotal">
                        <fo:table-row>
                            <fo:table-cell padding="2px 0px 1px 1px">
                                <fo:block>Sub Total</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="2px 1px 1px 0px">
                                <fo:block>&#160;</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="2px 2px 1px 0px" font-style="italic" text-align="right">
                                <fo:block>
                                    <xsl:value-of
                                            select="format-number(pageBillsSubTotal, '###.###.##0,00', 'decimalFormat')"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:when>
                    <xsl:otherwise>
                        <fo:table-row>
                            <fo:table-cell>
                                <fo:block></fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:choose>
                    <xsl:when test="isLast = 'true'">
                        <fo:table-row>
                            <fo:table-cell padding="2px 0px 1px 1px">
                                <fo:block>Sub Total</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="2px 1px 1px 0px">
                                <fo:block>&#160;</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="2px 2px 1px 0px" font-style="italic" text-align="right">
                                <fo:block>
                                    <xsl:value-of
                                            select="format-number(subTotal, '###.###.##0,00', 'decimalFormat')"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding="1px 0px 1px 2px">
                                <fo:block>Dto</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 1px 1px 0px" font-style="italic">
                                <fo:block>
                                    <xsl:value-of select="discountCode"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 2px 1px 0px" font-style="italic" text-align="right">
                                <fo:block>
                                    <xsl:value-of
                                            select="format-number(discount, '###.###.##0,00', 'decimalFormat')"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding="1px 0px 1px 2px">
                                <fo:block>Neto</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 1px 1px 0px">
                                <fo:block>&#160;</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 2px 1px 0px" font-style="italic" text-align="right">
                                <fo:block>
                                    <xsl:value-of
                                            select="format-number(netSubTotal, '###.###.##0,00', 'decimalFormat')"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding="1px 0px 1px 2px">
                                <fo:block>A Cuenta</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 1px 1px 0px">
                                <fo:block>&#160;</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 2px 1px 0px" font-style="italic" text-align="right">
                                <fo:block>0,00</fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding="1px 0px 1px 2px">
                                <fo:block>Total</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 1px 1px 0px">
                                <fo:block>&#160;</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 2px 1px 0px" font-style="italic" text-align="right">
                                <fo:block>
                                    <xsl:value-of
                                            select="format-number(voucherTotal, '###.###.##0,00', 'decimalFormat')"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:when>
                </xsl:choose>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="cuerpoReciboDerecha">
        <fo:table table-layout="fixed" width="94%" border="solid 0.3mm black" font-size="8pt">
            <fo:table-column column-width="20%"/>
            <fo:table-column column-width="20%"/>
            <fo:table-column column-width="25%"/>
            <fo:table-column column-width="15%"/>
            <fo:table-column column-width="20%"/>
            <fo:table-body>
                <fo:table-row font-style="italic" text-align="center">
                    <fo:table-cell padding="3px 0px 3px 0px" border="solid 0.3mm black">
                        <fo:block>C.U.I.T.</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="3px 0px 3px 0px" border="solid 0.3mm black">
                        <fo:block>Talón</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="3px 0px 3px 0px" border="solid 0.3mm black">
                        <fo:block>Banco</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="3px 0px 3px 0px" border="solid 0.3mm black">
                        <fo:block>Fecha</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="3px 0px 3px 0px" border="solid 0.3mm black">
                        <fo:block>Importe</fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <xsl:choose>
                    <xsl:when test="previousPagePaysSubTotal > 0">
                        <fo:table-row>
                            <fo:table-cell padding="1px 0px 1px 2px" border-right="solid 0.3mm black">
                                <fo:block>Arrastre</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 0px 1px 2px" border-right="solid 0.3mm black"
                                           text-align="center">
                                <fo:block></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 1px 1px 2px" border-right="solid 0.3mm black"
                                           text-align="right">
                                <fo:block></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 1px 1px 2px" border-right="solid 0.3mm black"
                                           text-align="right">
                                <fo:block></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 1px 1px 0px" text-align="right">
                                <fo:block>
                                    <xsl:value-of
                                            select="format-number(previousPagePaysSubTotal, '###.###.##0,00', 'decimalFormat')"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:when>
                </xsl:choose>
                <xsl:for-each select="payItems">
                    <fo:table-row>
                        <fo:table-cell padding="1px 0px 1px 2px" border-right="solid 0.3mm black">
                            <fo:block>
                                <xsl:value-of select="cuit"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="1px 0px 1px 2px" border-right="solid 0.3mm black">
                            <fo:block>
                                <xsl:value-of select="counterfoil"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="1px 0px 1px 2px" border-right="solid 0.3mm black">
                            <fo:block>
                                <xsl:value-of select="bank"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="1px 1px 1px 2px" border-right="solid 0.3mm black" text-align="center">
                            <fo:block>
                                <xsl:value-of select="date"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="1px 2px 1px 0px" text-align="right">
                            <fo:block>
                                <xsl:value-of select="format-number(amount, '###.###.##0,00', 'decimalFormat')"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:for-each>
                <xsl:choose>
                    <xsl:when test="pagePaysSubTotal">
                        <fo:table-row>
                            <fo:table-cell padding="1px 0px 1px 1px" border="solid 0.3mm black">
                                <fo:block></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 0px 1px 0px" border="solid 0.3mm black">
                                <fo:block></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 1px 1px 0px" border="solid 0.3mm black">
                                <fo:block>Sub total</fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 1px 1px 0px" border="solid 0.3mm black"
                                           text-align="center">
                                <fo:block></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="1px 1px 1px 0px" border="solid 0.3mm black" text-align="right">
                                <fo:block>
                                    <xsl:value-of
                                            select="format-number(pagePaysSubTotal, '###.###.##0,00', 'decimalFormat')"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:when>
                </xsl:choose>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="seccionTotales">
        <xsl:choose>
            <xsl:when test="isLast = 'true'">
                <fo:block>
                    <fo:table table-layout="fixed" width="100%" border="none" font-size="8pt">
                        <fo:table-column column-width="55%"/>
                        <fo:table-column column-width="10%"/>
                        <fo:table-column column-width="15%"/>
                        <fo:table-column column-width="20%"/>
                        <fo:table-body>
                            <xsl:choose>
                                <xsl:when test="chequeTotal > 0">
                                    <fo:table-row>
                                        <fo:table-cell padding="10px 0px 0px 0px">
                                            <fo:block>&#160;</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="10px 0px 0px 0px" text-align="right">
                                            <fo:block>Cheque</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="10px 0px 0px 0px" text-align="right">
                                            <fo:block>
                                                <xsl:value-of
                                                        select="format-number(chequeTotal, '###.###.##0,00', 'decimalFormat')"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2px 0px 0px 0px">
                                            <fo:block>&#160;</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:when>
                            </xsl:choose>
                            <xsl:choose>
                                <xsl:when test="cashTotal > 0">
                                    <fo:table-row>
                                        <fo:table-cell padding="2px 0px 0px 0px">
                                            <fo:block>&#160;</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2px 0px 0px 0px" text-align="right">
                                            <fo:block>Efectivo</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2px 0px 0px 0px" text-align="right">
                                            <fo:block>
                                                <xsl:value-of
                                                        select="format-number(cashTotal, '###.###.##0,00', 'decimalFormat')"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2px 0px 0px 0px">
                                            <fo:block>&#160;</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:when>
                            </xsl:choose>
                            <xsl:choose>
                                <xsl:when test="retentionsTotal > 0">
                                    <fo:table-row>
                                        <fo:table-cell padding="10px 0px 0px 0px">
                                            <fo:block>&#160;</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="10px 0px 0px 0px" text-align="right">
                                            <fo:block>Retenciones</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="10px 0px 0px 0px" text-align="right">
                                            <fo:block>
                                                <xsl:value-of
                                                        select="format-number(retentionsTotal, '###.###.##0,00', 'decimalFormat')"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2px 0px 0px 0px">
                                            <fo:block>&#160;</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:when>
                            </xsl:choose>
                            <fo:table-row>
                                <fo:table-cell padding="2px 0px 0px 0px">
                                    <fo:block>&#160;</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="2px 0px 0px 0px" text-align="right" font-weight="bold"
                                               font-style="italic">
                                    <fo:block>Total</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="2px 0px 0px 0px" text-align="right" font-weight="bold"
                                               font-style="italic">
                                    <fo:block>
                                        <xsl:value-of
                                                select="format-number(voucherTotal, '###.###.##0,00', 'decimalFormat')"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="2px 0px 0px 0px">
                                    <fo:block>&#160;</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </fo:block>
            </xsl:when>
            <xsl:otherwise>
                <fo:block>&#160;</fo:block>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="seccionNumeroPagina">
        <fo:table table-layout="fixed" bottom="0" width="95%" border="none" font-size="8pt" font-style="italic"
                  text-align="right">
            <fo:table-column column-width="100%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell padding="8px 0px 8px 0px">
                        <fo:block>Página
                            <xsl:value-of select="position()"/> de
                            <xsl:value-of select="last()"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>


</xsl:stylesheet>