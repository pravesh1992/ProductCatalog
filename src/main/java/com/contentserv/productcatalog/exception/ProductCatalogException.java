package com.contentserv.productcatalog.exception;

public class ProductCatalogException extends Exception {

  private final String errorMessage;

  ProductCatalogException(String errorMessage) {
    super(errorMessage);
    this.errorMessage = errorMessage;
  }

  ProductCatalogException(String errorMessage, Throwable throwable) {
    super(errorMessage, throwable);
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}