package com.contentserv.productcatalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductCatalogExceptionHandlerControllerAdvice {

  @ExceptionHandler(Exception.class)
  final ResponseEntity<ProductCatalogErrorResponse> handleException(Exception exception) {
    ProductCatalogErrorResponse productCatalogErrorResponse = new ProductCatalogErrorResponse(
      ProductCatalogExceptionUtils.analyseException(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(productCatalogErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}