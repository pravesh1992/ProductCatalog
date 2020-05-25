package com.contentserv.productcatalog.controllers;

import com.contentserv.productcatalog.services.ProductQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/simple")
public class ProductQualityController {

  @Autowired
  private ProductQualityService productQualityService;

  @RequestMapping(method = RequestMethod.POST, path = "/product-quality/{id}")
  @ResponseBody
  public ResponseEntity<String> validateProductQuality(@PathVariable(name = "id") Integer id) {
    String result = productQualityService.validateProductQuality(id);
    return new ResponseEntity<>("Product Quality of id:" + id + " is :" + result, HttpStatus.OK);
  }
}