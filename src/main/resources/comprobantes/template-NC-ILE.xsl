<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
    <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>

    <xsl:include href="template-nota-credito-elementos-genericos.xsl"/>

    <xsl:template name="datosClienteDerecha">
        <fo:block border="solid 0.1mm black" font-size="7pt" text-align="left" padding-left="3px">
            <fo:block padding="6px 0px 0px 0px" text-decoration="underline" text-align="center">
                CONDICION DE PAGO
            </fo:block>
            <fo:table table-layout="fixed" width="100%" border="none">
                <fo:table-column column-width="50%"/>
                <fo:table-column column-width="50%"/>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block padding="6px 0px 6px 0px">
                                Fecha vencimiento:
                            </fo:block>
                            <fo:block padding="6px 0px 6px 0px">
                                Condición de venta:
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block padding="6px 0px 6px 0px">
                                <xsl:value-of select="voucher/voucherDueDate"/>
                            </fo:block>
                            <fo:block padding="6px 0px 6px 0px">
                                <xsl:value-of select="voucher/paymentCondition"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>

    <xsl:template name="datosEmpresa">
        <fo:block font-size="8pt" text-align="left" padding="6px 0px 6px 6px">
            <fo:block>
                C.U.I.T.: 30-71082365-7
            </fo:block>
            <fo:block>
                ING. BRUTOS: 901-728596-7
            </fo:block>
            <fo:block>
                IMPUESTOS INTERNOS: NO RESPONSABLE
            </fo:block>
            <fo:block>
                INICIO DE ACTIVIDADES: 05/11/2008
            </fo:block>
        </fo:block>
    </xsl:template>

    <xsl:template name="logo">
        <fo:block text-align="left">
            <fo:external-graphic src="url('https://sla-comprobantes.s3-us-west-2.amazonaws.com/logos/logo-ilegor.png')"
                                 content-height="25mm"
                                 scaling="uniform"/>
        </fo:block>
    </xsl:template>

    <xsl:template name="anguloIzquierdo">
        <xsl:param name="copy"/>
        <fo:block font-size="8pt" text-align="left">
            <fo:block>
                <xsl:call-template name="logoTipoNotaCredito">
                    <xsl:with-param name="copy" select="$copy"/>
                </xsl:call-template>
            </fo:block>
            <fo:block>
                Av Los Incas 5091
            </fo:block>
            <fo:block>
                Capital Federal              1000
            </fo:block>
            <fo:block>
                Argentina          Consultas y Ventas          Tel: 0810-122-0787
            </fo:block>
        </fo:block>
    </xsl:template>

    <xsl:template name="aclaracionesAlPie">
        <fo:block font-size="7pt" margin-top="5px">
        </fo:block>
        <fo:block font-size="7pt" margin-bottom="5px">
        </fo:block>
    </xsl:template>

    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:schemaLocation="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="10px"
                                       margin-bottom="10px" margin-left="10px" margin-right="10px">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body">
                    <xsl:for-each select="DocumentWrapper/root">
                        <fo:block font-size="10pt">
                            <xsl:call-template name="notaCredito">
                                <xsl:with-param name="copy" select='"ORIGINAL"'/>
                            </xsl:call-template>
                        </fo:block>
                    </xsl:for-each>
                    <xsl:for-each select="DocumentWrapper/root">
                        <fo:block font-size="10pt">
                            <xsl:call-template name="notaCredito">
                                <xsl:with-param name="copy" select='"DUPLICADO"'/>
                            </xsl:call-template>
                        </fo:block>
                    </xsl:for-each>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>