package com.onready.pdf.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QRJson {

  private int ver = 1;
  private String fecha;
  private Long cuit;
  private int ptoVta;
  private int tipoCmp;
  private int nroCmp;
  private BigDecimal importe;
  private String moneda = "PES";
  private int ctz = 1;
  private int tipoDocRec = 80;
  private Long nroDocRec;
  private String tipoCodAut;
  private Long codAut;

}
