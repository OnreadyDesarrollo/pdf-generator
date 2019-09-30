package com.onready.pdf.utils;

public class DocumentWrapper {

  private Object root;

  private DocumentWrapper() {
  }

  public DocumentWrapper(Object rootElement) {
    this.root = rootElement;
  }

  public Object getRoot() {
    return root;
  }

  public void setRoot(Object root) {
    this.root = root;
  }
}
