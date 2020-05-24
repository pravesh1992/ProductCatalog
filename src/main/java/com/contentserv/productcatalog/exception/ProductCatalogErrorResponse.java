package com.contentserv.productcatalog.exception;

import org.springframework.http.HttpStatus;

public class ProductCatalogErrorResponse {
  private int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
  private String error = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
  private String message = "Internal Server Error";


  ProductCatalogErrorResponse(ProductCatalogException flamingoException, HttpStatus httpStatus) {
    if (flamingoException != null) {
      this.message = flamingoException.getErrorMessage();
      this.status = httpStatus.value();
      this.error = httpStatus.getReasonPhrase();
    }
  }
}
