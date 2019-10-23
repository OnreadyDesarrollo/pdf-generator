package com.onready.pdf.domain;

public enum LogoFilenameEnum {

  CRO("cromosol.png"),
  BBA("bba.jpg"),
  AVE("avellaneda.jpg");

  private final String filename;

  LogoFilenameEnum(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return filename;
  }
}
