package com.contentserv.productcatalog.controllers;

import com.contentserv.productcatalog.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/simple")
public class ProductCategoryController {

  @Autowired
  private ProductCategoryService productCategoryService;

  @RequestMapping(method = RequestMethod.POST, path = "/product-category/{name}")
  @ResponseBody
  public ResponseEntity<String> saveProductCategory(@PathVariable(name = "name") String name, @RequestBody List<String> modelingKeys) {
    productCategoryService.saveProductCategory(name, modelingKeys);
    return new ResponseEntity<>("Product category " + name + " is added successfully", HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/product-category/{name}")
  @ResponseBody
  public List<String> fetchProductCategoryModelingAttributes(@PathVariable(name = "name") String name) {
    return productCategoryService.fetchProductCategoryModelingAttributes(name);
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/product-category/{name}")
  @ResponseBody
  public void deleteProductCategory(@PathVariable(name = "name") String name) {
    productCategoryService.deleteProductCategory(name);
  }
}