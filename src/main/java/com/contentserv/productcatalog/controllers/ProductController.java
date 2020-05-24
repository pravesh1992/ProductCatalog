package com.contentserv.productcatalog.controllers;

import com.contentserv.productcatalog.business.objects.Product;
import com.contentserv.productcatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/simple")
public class ProductController {

  @Autowired
  private ProductService productService;

  @RequestMapping(method = RequestMethod.GET, path = "/products/{id}")
  public Product getProduct(@PathVariable(name = "id") Integer id) {
    return productService.getProduct(id);
  }

  @RequestMapping(method = RequestMethod.POST, path = "/products")
  @ResponseBody
  public ResponseEntity<String> saveProduct(@RequestBody Product product) {
    Integer productId = productService.saveProduct(product);
    return new ResponseEntity<>("Product added successfully. Product IdL" + productId, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/products")
  @ResponseBody
  public Product updateProduct(@RequestBody Product product) {
    return productService.updateProduct(product);
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/products/{id}")
  public void deleteProduct(@PathVariable(name = "id") Integer id) {
    productService.deleteProduct(id);
  }
}