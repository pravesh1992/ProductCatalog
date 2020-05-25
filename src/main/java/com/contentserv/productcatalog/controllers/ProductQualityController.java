package com.contentserv.productcatalog.controllers;

import com.contentserv.productcatalog.services.ProductQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/simple")
public class ProductQualityController {

  @Autowired
  private ProductQualityService productQualityService;

}