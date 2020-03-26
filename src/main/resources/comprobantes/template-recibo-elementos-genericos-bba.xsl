<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:border-top="http://www.w3.org/1999/xhtml"
                exclude-result-prefixes="fo">

    <xsl:decimal-format name="decimalFormat" decimal-separator="," grouping-separator="."/>
    <xsl:variable name="smallcase" select="'abcdefghijklmnñopqrstuvwxyz'"/>
    <xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNÑOPQRSTUVWXYZ'"/>

    <xsl:template name="reciboBba">
        <xsl:param name="copy"/>
        <fo:table>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:call-template name="cabecera">
                                <xsl:with-param name="copy" select='$copy'/>
                            </xsl:call-template>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="cabecera">
        <xsl:param name="copy"/>
        <fo:table table-layout="fixed" width="100%" border="solid 0.3mm black">
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:call-template name="cabeceraIzq"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:call-template name="cabeceraDer"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:call-template name="datosClienteIzq"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:call-template name="datosClienteDer"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="cabeceraIzq">
        <fo:table table-layout="fixed" border-left="solid 0.15mm black">
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell padding="10px">
                        <fo:block></fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell text-align="right" display-align="before" padding="5px 10px 5px 5px">
                        <fo:block font-weight="bold" font-size="9pt">
                            BBA AUTOPARTES S.A.
                        </fo:block>
                        <fo:block font-size="7pt">
                            Av. Roberto Hooke 3754 | 1667 | Tortuguitas | N
                        </fo:block>
                        <fo:block font-size="7pt">
                            Tel. +54 11 4554 0267 | Fax: +54 11 4554 0267 | eMail bba@bbaautopartes.com
                        </fo:block>
                        <fo:block font-size="7pt">
                            Responsable Inscripto |
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="cabeceraDer">
        <fo:table table-layout="fixed" border-left="solid 0.15mm black">
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell text-align="center" font-size="8pt" padding="4px 0px 4px 4px"
                                   border-left="solid 0.15mm black">
                        <fo:block font-weight="bold">
                            Recibo de cobranzas
                        </fo:block>
                        <fo:block padding="0px 4px 2px ">
                            2RCX11
                            <xsl:value-of select="receipt/formattedNumber"/>
                        </fo:block>
                        <fo:block>
                            Fecha:
                            <xsl:value-of select="receipt/voucherDate"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell text-align="left" font-size="7pt" padding="0px 0px 12px 5px"
                                   border-left="solid 0.15mm black">
                        <fo:block>
                            C.U.I.T.: 30-71128531-4
                        </fo:block>
                        <fo:block>
                            ING. BRUTOS.: 901-550597-3
                        </fo:block>
                        <fo:block>
                            INICIO DE ACTIVIDAD: 1/1/1980
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="datosClienteIzq">
        <fo:table table-layout="fixed" border-top="solid 0.3mm black">
            <fo:table-column column-width="16%"/>
            <fo:table-column column-width="84%"/>
            <fo:table-body font-size="8pt">
                <fo:table-row>
                    <fo:table-cell padding="2px 0px 2px 4px">
                        <fo:block>
                            Cliente:
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="2px 0px 0px 0px" font-weight="bold">
                        <fo:block>
                            <xsl:value-of select="receipt/businessName"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell padding="0px 0px 2px 4px">
                        <fo:block>
                            Dirección:
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell font-weight="bold">
                        <fo:block>
                            <xsl:value-of select="receipt/address"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell padding="0px 0px 2px 4px">
                        <fo:block>
                            Teléfono:
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell font-weight="bold">
                        <fo:block>
                            FALTA CAMPO
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="datosClienteDer">
        <fo:table table-layout="fixed" border-top="solid 0.3mm black">
            <fo:table-column column-width="25%"/>
            <fo:table-column column-width="75%"/>
            <fo:table-body font-size="8pt" >
                <fo:table-row>
                    <fo:table-cell padding="2px 0px 2px 10px" font-weight="bold">
                        <fo:block>
                            (        <xsl:value-of select="receipt/customerId"/>        )
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="2px 0px 0px 0px" font-weight="bold">
                        <fo:block>
                            C.U.I.T.
                            <xsl:value-of select="receipt/customerCuit"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell padding="0px 0px 2px 10px" font-weight="bold">
                        <fo:block>
                            (        FALTA CAMPO        )
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell font-weight="bold">
                        <fo:block>
                            <xsl:value-of select="receipt/city"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell padding="0px 2px 2px 10px">
                        <fo:block>
                            Provincia:
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell font-weight="bold">
                        <fo:block>
                            <xsl:value-of select="receipt/state"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template name="cuerpoRecibo">
    </xsl:template>

    <xsl:template name="cuerpoReciboIzquierda">
        <fo:table table-layout="fixed" width="100%" border="solid 0.3mm black" font-size="8pt">

        </fo:table>
        <fo:table table-layout="fixed" width="100%" border-left="solid 0.3mm black" border-right="solid 0.3mm black"
                  border-bottom="solid 0.3mm black" font-size="8pt">
        </fo:table>
    </xsl:template>

    <xsl:template name="cuerpoReciboDerecha">
        <fo:table table-layout="fixed" width="94%" border="solid 0.3mm black" font-size="8pt">
        </fo:table>
    </xsl:template>

    <xsl:template name="seccionTotales">
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