package com.contentserv.productcatalog.services;

import com.contentserv.productcatalog.repositores.ProductCategoryRepository;
import com.contentserv.productcatalog.repositores.ProductModelingRepository;
import com.contentserv.productcatalog.repositores.db.objects.DBProductCategory;
import com.contentserv.productcatalog.repositores.db.objects.DBProductModelingAttribute;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

  @Autowired
  private ProductCategoryRepository productCategoryRepository;

  @Autowired
  private ProductModelingRepository productModelingRepository;

  public String saveProductCategory(String name, List<String> modelingKeys) {
    DBProductCategory dbProductCategory = productCategoryRepository.findOneByName(name);
    if (dbProductCategory == null) {
      dbProductCategory = new DBProductCategory();
      dbProductCategory.setName(name);
      productCategoryRepository.save(dbProductCategory);
    }

    if (!CollectionUtils.isEmpty(modelingKeys)) {
      List<DBProductModelingAttribute> dbProductModelingAttributes = new ArrayList<>();
      for (String modelingKey : modelingKeys) {
        dbProductModelingAttributes.add(new DBProductModelingAttribute(modelingKey, dbProductCategory));
      }
      productModelingRepository.saveAll(dbProductModelingAttributes);
    }

    return name;
  }

  public List<String> fetchProductCategoryModelingAttributes(String name) {
    DBProductCategory dbProductCategory = productCategoryRepository.findOneByName(name);
    if (dbProductCategory == null) {
      throw new RuntimeException("No product category exists with name:" + name);
    }
    return productModelingRepository.findAllProductModelingAttributes(dbProductCategory.getId()).stream().map(DBProductModelingAttribute::getName).collect(Collectors.toList());
  }

  public void deleteProductCategory(String name) {
    DBProductCategory dbProductCategory = productCategoryRepository.findOneByName(name);
    if (dbProductCategory != null) {
      productCategoryRepository.delete(dbProductCategory);
    }
  }
}