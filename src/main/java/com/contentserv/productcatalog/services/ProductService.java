package com.contentserv.productcatalog.services;

import com.contentserv.productcatalog.business.objects.Entry;
import com.contentserv.productcatalog.business.objects.Product;
import com.contentserv.productcatalog.repositores.ProductCategoryRepository;
import com.contentserv.productcatalog.repositores.ProductRepository;
import com.contentserv.productcatalog.repositores.db.objects.DBProduct;
import com.contentserv.productcatalog.repositores.db.objects.DBProductCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductCategoryRepository productCategoryRepository;

  public Product getProduct(Integer id) {
    Optional<DBProduct> optionalDBProduct = productRepository.findById(id);
    if (optionalDBProduct.isPresent()) {
      return convert(optionalDBProduct.get());
    }

    throw new RuntimeException("No Product found with id:" + id);
  }

  public Integer saveProduct(Product product) {
    if (product.getId() != null) {
      if (productRepository.findById(product.getId()).isPresent())
        throw new RuntimeException("Product already added with id:" + product.getId());
    }

    return productRepository.save(convert(product)).getId();
  }

  public Product updateProduct(Product product) {
    if (product.getId() == null) {
      throw new RuntimeException("Product id can't be null for update operations");
    }
    Optional<DBProduct> optionalProduct = productRepository.findById(product.getId());
    if (!optionalProduct.isPresent())
      throw new RuntimeException("Product does not exists with id:" + product.getId());


    DBProduct dbProduct = convert(product);
    dbProduct.setId(optionalProduct.get().getId());
    return convert(productRepository.save(dbProduct));
  }


  public void deleteProduct(Integer id) {
    if (productRepository.findById(id).isPresent()) {
      productRepository.deleteById(id);
    }
    throw new RuntimeException("No product found with id:" + id);
  }

  DBProduct convert(Product product) {
    DBProduct dbProduct = new DBProduct();
    dbProduct.setName(product.getName());
    if (StringUtils.isBlank(product.getCategory())) {
      throw new RuntimeException("Category can't be null");
    }

    DBProductCategory productCategory = productCategoryRepository.findOneByName(product.getCategory());
    if (productCategory == null)
      throw new RuntimeException("No product category exists:" + product.getCategory());
    dbProduct.setDbProductCategory(productCategory);
    dbProduct.setDescription(product.getDescription());
    if (product.getModelingKeys() != null && !product.getModelingKeys().isEmpty()) {
      List<String> modelingKeys = product.getModelingKeys().stream().map(item -> item.getKey() + ":" + item.getValue()).collect(Collectors.toList());
      dbProduct.setModelingKeys(String.join("#", modelingKeys));
    }
    return dbProduct;
  }

  public static Product convert(DBProduct dbProduct) {
    Product product = new Product();
    product.setId(dbProduct.getId());
    product.setName(dbProduct.getName());
    product.setCategory(dbProduct.getDbProductCategory().getName());
    product.setDescription(dbProduct.getDescription());
    Set<Entry> modelingKeys = new HashSet<>();
    if (!StringUtils.isBlank(dbProduct.getModelingKeys())) {
      List<String> modelingKeysWithHash = Arrays.asList(dbProduct.getModelingKeys().split("#"));
      for (String modelKey : modelingKeysWithHash) {
        String[] keyValue = modelKey.split(":");
        modelingKeys.add(new Entry(keyValue[0], keyValue[1]));
      }
    }
    product.setModelingKeys(modelingKeys);
    return product;
  }
}