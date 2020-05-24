package com.contentserv.productcatalog.exception;

public class ProductCatalogExceptionUtils {

  public static ProductCatalogException analyseException(Exception e) {
    return new ProductCatalogException(e.getMessage(), e);
  }
}
