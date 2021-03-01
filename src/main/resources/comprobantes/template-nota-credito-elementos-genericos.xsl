<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:decimal-format name="decimalFormat" decimal-separator="," grouping-separator="."/>
    <xsl:variable name="smallcase" select="'abcdefghijklmnñopqrstuvwxyz'"/>
    <xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNÑOPQRSTUVWXYZ'"/>

    <xsl:template name="notaCredito">
        <xsl:param name="copy"/>
        <fo:block>
            <fo:block>
                <xsl:call-template name="cabecera">
                    <xsl:with-param name="copy" select='$copy'/>
                </xsl:call-template>
            </fo:block>
            <fo:block>
                <xsl:call-template name="datosCliente">
                </xsl:call-template>
            </fo:block>
            <fo:block>
                <xsl:call-template name="cuerpoNotaCredito">
                </xsl:call-template>
            </fo:block>
            <fo:block>
                <xsl:call-template name="observaciones">
                </xsl:call-template>
            </fo:block>
            <fo:block>
                <xsl:call-template name="formasPago">
                </xsl:call-template>
            </fo:block>
            <fo:block>
                <xsl:call-template name="seccionTotales">
                </xsl:call-template>
            </fo:block>
            <fo:block>
                <xsl:call-template name="aclaracionesAlPie">
                </xsl:call-template>
            </fo:block>
            <fo:block>
                <xsl:call-template name="pie">
                </xsl:call-template>
            </fo:block>
        </fo:block>
    </xsl:template>

    <xsl:template name="cabecera">
        <xsl:param name="copy"/>
        <fo:table table-layout="fixed" width="100%" border="none">
            <fo:table-column column-width="66%"/>
            <fo:table-column column-width="34%"/>
            <fo:table-body>
                <fo:table-row font-size="12pt">
                    <fo:table-cell text-align="center" padding="6px 0px 6px 0px">
                        <xsl:call-template name="anguloIzquierdo">
                            <xsl:with-param name="copy" select='$copy'/>
                        </xsl:call-template>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="6px 0px 6px 0px">
                        <xsl:call-template name="anguloDerecho">
                        </xsl:call-template>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="anguloDerecho">
        <fo:block border="solid 0.1mm black" padding-left="3px">
            <fo:block>
                <xsl:call-template name="numeroNotaCredito">
                </xsl:call-template>
            </fo:block>
            <fo:block>
                <xsl:call-template name="datosEmpresa">
                </xsl:call-template>
            </fo:block>
        </fo:block>
    </xsl:template>

    <xsl:template name="numeroNotaCredito">
        <fo:block>
            <fo:inline-container inline-progression-dimension="20%" font-size="26pt">
                <fo:block>
                    N°
                </fo:block>
            </fo:inline-container>
            <fo:inline-container inline-progression-dimension="80%" font-size="12pt">
                <fo:block>
                    <xsl:value-of select="voucher/formattedNumber"/>
                </fo:block>
            </fo:inline-container>
        </fo:block>
        <fo:block font-size="8pt" text-align="left">
            FECHA:
            <xsl:value-of select="voucher/voucherDate"/>
        </fo:block>
    </xsl:template>

    <xsl:template name="logoTipoNotaCredito">
        <xsl:param name="copy"/>
        <fo:block>
            <fo:table table-layout="fixed" width="100%" border="none">
                <fo:table-column column-width="75%"/>
                <fo:table-column column-width="25%"/>
                <fo:table-body>
                    <fo:table-row font-size="12pt">
                        <fo:table-cell text-align="center" padding="6px 0px 6px 0px">
                            <xsl:call-template name="logo">
                            </xsl:call-template>
                        </fo:table-cell>
                        <fo:table-cell text-align="center" padding="6px 0px 6px 0px">
                            <fo:block>
                                <xsl:value-of select="$copy"/>
                            </fo:block>
                            <fo:block font-size="32pt">
                                <xsl:value-of select="voucher/voucherLetter"/>
                            </fo:block>
                            <fo:block>
                                NOTA DE CREDITO
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>

    <xsl:template name="datosCliente">
        <fo:block>
            <fo:table table-layout="fixed" width="100%" border="none">
                <fo:table-column column-width="60%"/>
                <fo:table-column column-width="40%"/>
                <fo:table-body>
                    <fo:table-row font-size="12pt">
                        <fo:table-cell text-align="center" padding="6px 0px 6px 0px">
                            <xsl:call-template name="datosClienteIzquierda">
                            </xsl:call-template>
                        </fo:table-cell>
                        <fo:table-cell text-align="center" padding="6px 0px 6px 0px">
                            <xsl:call-template name="datosClienteDerecha">
                            </xsl:call-template>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>

    <xsl:template name="datosClienteIzquierda">
        <fo:block border="solid 0.1mm black" margin-right="5px" text-align="left" font-size="8pt">
            <fo:table table-layout="fixed" width="100%" border="none">
                <fo:table-column column-width="15%"/>
                <fo:table-column column-width="60%"/>
                <fo:table-column column-width="20%"/>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block font-size="12pt" padding="6px 0px 6px 0px">
                                SR./ES:
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block padding="6px 0px 6px 0px">
                                <xsl:value-of select="voucher/businessName"/>
                            </fo:block>
                            <fo:block padding="0px 0px 6px 0px">
                                <xsl:value-of select="voucher/address"/>
                            </fo:block>
                            <fo:block padding="0px 0px 6px 0px">
                                <xsl:value-of select="voucher/city"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block padding="6px 0px 6px 0px">
                                <xsl:value-of select="voucher/customerId"/>
                            </fo:block>
                            <fo:block padding="0px 0px 6px 0px">
                                <xsl:value-of select="voucher/sellerCode"/>
                            </fo:block>
                            <fo:block padding="0px 0px 6px 0px">
                                <xsl:value-of select="voucher/state"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>

    <xsl:template name="cuerpoNotaCredito">
        <fo:block-container height="13cm" border="solid 0.1mm black" border-bottom-style="hidden">
            <fo:table table-layout="fixed" width="100%">
                <fo:table-column column-width="6%"/>
                <fo:table-column column-width="10%"/>
                <fo:table-column column-width="48%"/>
                <fo:table-column column-width="15%"/>
                <fo:table-column column-width="12%"/>
                <fo:table-column column-width="9%"/>
                <fo:table-body>
                    <fo:table-row font-size="10pt">
                        <fo:table-cell text-align="center" padding="3px 0px 3px 0px" border="solid 0.1mm black">
                            <fo:block>CANT.</fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="center" padding="3px 0px 3px 0px" border="solid 0.1mm black">
                            <fo:block>ARTICULO</fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="center" padding="3px 0px 3px 0px" border="solid 0.1mm black">
                            <fo:block>DESCRIPCION</fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="center" padding="3px 0px 3px 0px" border="solid 0.1mm black">
                            <fo:block>DESP. IMP.</fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="center" padding="3px 0px 3px 0px" border="solid 0.1mm black">
                            <fo:block>PRECIO UNIT.</fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="center" padding="3px 0px 3px 0px" border="solid 0.1mm black">
                            <fo:block>IMPORTE</fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <xsl:choose>
                        <xsl:when test="previousPageSubTotal != ''">
                            <fo:table-row font-size="9pt" font-style="italic" font-weight="bold">
                                <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                                    <fo:block/>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                                    <fo:block/>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                                    <fo:block/>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" padding="1px 0px 1px 0px"
                                               border-bottom="solid 0.2mm black">
                                    <fo:block/>
                                </fo:table-cell>
                                <fo:table-cell text-align="right" padding="3px 0px 5px 0px"
                                               border-bottom="solid 0.2mm black">
                                    <fo:block>Transporte</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="right" padding="3px 2px 5px 0px"
                                               border-bottom="solid 0.2mm black">
                                    <fo:block>
                                        <xsl:value-of
                                                select="format-number(previousPageSubTotal, '###.###.#00,00', 'decimalFormat')"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </xsl:when>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="voucher/company = 'BBA' or voucher/company = 'AUT'">
                            <xsl:call-template name="itemsBBA">
                            </xsl:call-template>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:call-template name="itemsCromosol">
                            </xsl:call-template>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="pageSubTotal != ''">
                            <fo:table-row font-size="9pt" font-style="italic" font-weight="bold">
                                <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                                    <fo:block/>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                                    <fo:block/>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                                    <fo:block/>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" padding="1px 0px 1px 0px"
                                               border-top="solid 0.2mm black">
                                    <fo:block/>
                                </fo:table-cell>
                                <fo:table-cell text-align="right" padding="5px 0px 1px 0px"
                                               border-top="solid 0.2mm black">
                                    <fo:block>Subtotal</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="right" padding="5px 2px 1px 0px"
                                               border-top="solid 0.2mm black">
                                    <fo:block>
                                        <xsl:value-of
                                                select="format-number(pageSubTotal, '###.###.#00,00', 'decimalFormat')"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </xsl:when>
                    </xsl:choose>
                </fo:table-body>
            </fo:table>
        </fo:block-container>
    </xsl:template>

    <xsl:template name="itemsCromosol">
        <xsl:choose>
            <xsl:when test="voucher/observations != ''">
                <fo:table-row font-size="8pt">
                    <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                        <fo:block></fo:block>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                        <fo:block></fo:block>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="10px 0px 1px 0px">
                        <fo:block>
                            <xsl:value-of select="voucher/observations"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </xsl:when>
            <xsl:otherwise>
                <xsl:for-each select="items/items">
                    <fo:table-row font-size="8pt">
                        <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                            <fo:block>
                                <xsl:value-of select="quantity"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                            <fo:block>
                                <xsl:value-of select="articleId"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="left" padding="1px 0px 1px 0px">
                            <fo:block>
                                <xsl:value-of
                                        select="translate(articleDescription, $smallcase, $uppercase)"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                            <fo:block>
                                <xsl:value-of select="dispatch"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="right" padding="1px 0px 1px 0px">
                            <fo:block>
                                <xsl:value-of
                                        select="format-number(unitPrice, '###.###.#00,00', 'decimalFormat')"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="right" padding="1px 2px 1px 0px">
                            <fo:block>
                                <xsl:value-of
                                        select="format-number(totalAmount, '###.###.#00,00', 'decimalFormat')"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:for-each>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="itemsBBA">
        <xsl:for-each select="items/items">
            <fo:table-row font-size="8pt">
                <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                    <fo:block>
                        <xsl:value-of select="quantity"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                    <fo:block>
                        <xsl:value-of select="articleId"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell text-align="left" padding="1px 0px 1px 0px">
                    <fo:block>
                        <xsl:value-of
                                select="translate(articleDescription, $smallcase, $uppercase)"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell text-align="center" padding="1px 0px 1px 0px">
                    <fo:block>
                        <xsl:value-of select="dispatch"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell text-align="right" padding="1px 0px 1px 0px">
                    <fo:block>
                        <xsl:value-of
                                select="format-number(unitPrice, '###.###.#00,00', 'decimalFormat')"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell text-align="right" padding="1px 2px 1px 0px">
                    <fo:block>
                        <xsl:value-of
                                select="format-number(totalAmount, '###.###.#00,00', 'decimalFormat')"/>
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>
        </xsl:for-each>
    </xsl:template>

    <xsl:template name="observaciones">
        <fo:block height="1cm" border="solid 0.1mm black" border-top-style="hidden" padding-right="20px">
            <xsl:choose>
                <xsl:when test="voucher/company = 'BBA' or voucher/company = 'AUT'">
                    <fo:block text-indent="5mm" space-before="1mm" font-size="10pt">
                        <xsl:value-of select="voucher/observations"/>
                    </fo:block>
                </xsl:when>
            </xsl:choose>
        </fo:block>
    </xsl:template>

    <xsl:template name="seccionTotales">
        <fo:table table-layout="fixed" width="100%">
            <fo:table-column column-width="12%"/>
            <fo:table-column column-width="16%"/>
            <fo:table-column column-width="16%"/>
            <fo:table-column column-width="16%"/>
            <fo:table-column column-width="25%"/>
            <fo:table-column column-width="15%"/>
            <fo:table-body>
                <fo:table-row font-size="9pt">
                    <fo:table-cell text-align="center" padding="6px 0px 6px 0px" border="solid 0.1mm black">
                        <fo:block>SUBTOTAL</fo:block>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="6px 0px 6px 0px" border="solid 0.1mm black">
                        <fo:block>DESCUENTO</fo:block>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="6px 0px 6px 0px" border="solid 0.1mm black">
                        <fo:block>SUBTOTAL NETO</fo:block>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="6px 0px 6px 0px" border="solid 0.1mm black">
                        <fo:block>I.V.A. INSCRIPTO</fo:block>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="6px 0px 6px 0px" border="solid 0.1mm black">
                        <fo:block>PERC. IIBB
                            <xsl:value-of select="voucher/jurisdiction"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="6px 0px 6px 0px" border="solid 0.1mm black">
                        <fo:block>TOTAL</fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row font-size="10pt" border="solid 0.1mm black">
                    <fo:table-cell text-align="center" padding="6px 0px 0px 0px">
                        <xsl:choose>
                            <xsl:when test="not(pageSubTotal != '')">
                                <fo:block>
                                    <xsl:value-of
                                            select="format-number(voucher/subTotal, '###.###.#00,00', 'decimalFormat')"/>
                                </fo:block>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:block>&#160;</fo:block>
                            </xsl:otherwise>
                        </xsl:choose>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="6px 0px 0px 0px">
                        <xsl:choose>
                            <xsl:when test="not(pageSubTotal != '')">
                                <fo:block>
                                    <xsl:value-of
                                            select="format-number(voucher/discount, '###.###.#00,00', 'decimalFormat')"/>
                                </fo:block>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:block>&#160;</fo:block>
                            </xsl:otherwise>
                        </xsl:choose>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="6px 0px 0px 0px">
                        <xsl:choose>
                            <xsl:when test="not(pageSubTotal != '')">
                                <fo:block>
                                    <xsl:value-of
                                            select="format-number(voucher/netSubTotal, '###.###.#00,00', 'decimalFormat')"/>
                                </fo:block>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:block>&#160;</fo:block>
                            </xsl:otherwise>
                        </xsl:choose>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="6px 0px 0px 0px">
                        <xsl:choose>
                            <xsl:when test="not(pageSubTotal != '')">
                                <fo:block>
                                    <xsl:choose>
                                        <xsl:when test="(string(voucher/vatAmount))">
                                            <xsl:value-of
                                                    select="format-number(voucher/vatAmount, '###.###.#00,00', 'decimalFormat')"/>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block>00,00</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:block>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:block>&#160;</fo:block>
                            </xsl:otherwise>
                        </xsl:choose>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="6px 0px 0px 0px">
                        <xsl:choose>
                            <xsl:when test="not(pageSubTotal != '')">
                                <fo:block>
                                    <xsl:value-of
                                            select="format-number(voucher/grossIncomePerception, '###.###.#00,00', 'decimalFormat')"/>
                                </fo:block>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:block>&#160;</fo:block>
                            </xsl:otherwise>
                        </xsl:choose>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="6px 0px 0px 0px" border="solid 0.1mm black">
                        <xsl:choose>
                            <xsl:when test="not(pageSubTotal != '')">
                                <fo:block>
                                    <xsl:value-of
                                            select="format-number(voucher/voucherTotal, '###.###.#00,00', 'decimalFormat')"/>
                                </fo:block>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:block>&#160;</fo:block>
                            </xsl:otherwise>
                        </xsl:choose>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="formasPago">
        <fo:table table-layout="fixed" width="100%" border="none" margin-top="2px" margin-bottom="2px">
            <fo:table-column column-width="50%"/>
            <fo:table-column column-width="50%"/>
            <fo:table-body>
                <fo:table-row font-size="10pt">
                    <fo:table-cell text-align="center" padding="6px 0px 0px 0px" margin-right="2px"
                                   border="solid 0.1mm black">
                        <fo:block-container height="15mm">
                            <fo:block>
                            </fo:block>
                        </fo:block-container>
                    </fo:table-cell>
                    <fo:table-cell text-align="center" padding="6px 0px 0px 0px" border="solid 0.1mm black">
                        <fo:block-container height="15mm">
                            <fo:block>
                            </fo:block>
                        </fo:block-container>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="pie">
        <fo:table table-layout="fixed" width="100%">
            <fo:table-column column-width="18%"/>
            <fo:table-column column-width="32%"/>
            <fo:table-column column-width="50%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block text-align="center">
                            <xsl:element name="fo:external-graphic">
                                <xsl:attribute name="src">
                                    <xsl:value-of select="voucher/qr"/>
                                </xsl:attribute>
                            </xsl:element>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>&#160;</fo:block>
                        <fo:block>&#160;</fo:block>
                        <fo:block text-align="left">
                            <fo:external-graphic
                                    src="url('https://sla-comprobantes.s3-us-west-2.amazonaws.com/logos/afip.jpg')"
                                    content-width="27mm"/>
                        </fo:block>
                        <fo:block text-align="left" font-weight="bold" font-style="italic" font-size="9pt">
                            Comprobante autorizado
                        </fo:block>
                        <fo:block text-align="left" font-weight="bold" font-style="italic" font-size="6pt">
                            Esta administración federal no se responsabiliza por los datos ingresados en el detalle de
                            la operaeción
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:choose>
                            <xsl:when test="not(voucher/caieExpirationDate = '')
                and not(voucher/caie = null)
                and not(voucher/caie = '')">
                                <fo:block>&#160;</fo:block>
                                <fo:block>&#160;</fo:block>
                                <fo:block>&#160;</fo:block>
                                <fo:block>
                                    CAE N°:
                                    <xsl:value-of select="voucher/caie"/>
                                    - Vencimiento:
                                    <xsl:value-of select="voucher/caieExpirationDate"/>
                                </fo:block>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:block>&#160;</fo:block>
                            </xsl:otherwise>
                        </xsl:choose>
                        <fo:block margin-top="5px">
                            Página
                            <xsl:value-of select="position()"/> de
                            <xsl:value-of select="last()"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

</xsl:stylesheet>