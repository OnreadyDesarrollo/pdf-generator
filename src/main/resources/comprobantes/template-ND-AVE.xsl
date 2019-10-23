<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
    <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>

    <xsl:include href="template-nota-debito-elementos-genericos.xsl"/>

    <xsl:template name="datosClienteDerecha">
        <fo:block border="solid 0.1mm black" font-size="8pt" text-align="left" padding-left="3px">
            <fo:block padding="6px 0px 6px 0px">
                CONDICIONES: AL PIE.ENTREGA PAGO MAX 35 DIAS
            </fo:block>
            <fo:block padding="0px 0px 6px 0px">
                I.V.A.:
                <xsl:value-of select="voucher/vatCondition"/>
            </fo:block>
            <fo:block padding="0px 0px 6px 0px">
                C.U.I.T.:
                <xsl:value-of select="voucher/cuit"/>
            </fo:block>
        </fo:block>
    </xsl:template>

    <xsl:template name="datosEmpresa">
        <fo:block font-size="8pt" text-align="left" padding="6px 0px 6px 6px">
            <fo:block>
                C.U.I.T.: 30-71128531-4
            </fo:block>
            <fo:block>
                ING. BRUTOS: 901-550597-3
            </fo:block>
            <fo:block>
                IMPUESTOS INTERNOS: NO CORRESPONDE
            </fo:block>
            <fo:block>
                INICIO DE ACTIVIDADES: 19/02/2010
            </fo:block>
        </fo:block>
    </xsl:template>

    <xsl:template name="logo">
        <fo:block text-align="left">
            <fo:external-graphic src="{graphicsUri}"
                                 content-height="25mm"
                                 scaling="uniform"/>
        </fo:block>
    </xsl:template>

    <xsl:template name="anguloIzquierdo">
        <xsl:param name="copy" />
        <fo:block font-size="8pt" text-align="left">
            <fo:block>
                <xsl:call-template name="logoTipoNotaDebito">
                    <xsl:with-param name="copy" select="$copy"/>
                </xsl:call-template>
            </fo:block>
            <fo:block>
                BBA AUTOPARTES S.A. / Avenida Roberto Hooke 3754 / CP 1667 / Tortuguitas / Buenos Aires / Argentina
            </fo:block>
            <fo:block>
                Tel: 0800-122-6587 / www.tycautopartes.com / info@tycautopartes.com
            </fo:block>
        </fo:block>
    </xsl:template>

    <xsl:template name="aclaracionesAlPie">
        <fo:block font-size="6pt" margin-top="5px">
            LAS FACTURAS DEBEN SER ABONADAS EN BBA AUTOPARTES S.A. - AV. ROBERTO HOOKE 3754 - (CP 1667) TORTUGUITAS
            ARGENTINA - CHEQUES A LA ORDEN DE BBA AUTOPARTES S.A.
        </fo:block>
        <fo:block font-size="6pt" margin-bottom="5px">
            LA MERCADERIA VIAJA POR CUENTA Y RIESGO DEL COMPRADOR. NO SE ACEPTAN RECLAMOS DESPUES DE LAS 48HS. DE
            RECIBIDA LA MERCADERIA.
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
                            <xsl:call-template name="notaDebito">
                                <xsl:with-param name="copy" select='"ORIGINAL"' />
                            </xsl:call-template>
                        </fo:block>
                    </xsl:for-each>
                    <xsl:for-each select="DocumentWrapper/root">
                        <fo:block font-size="10pt">
                            <xsl:call-template name="notaDebito">
                                <xsl:with-param name="copy" select='"DUPLICADO"' />
                            </xsl:call-template>
                        </fo:block>
                    </xsl:for-each>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>