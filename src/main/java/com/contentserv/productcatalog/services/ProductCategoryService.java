package com.contentserv.productcatalog.services;

import com.contentserv.productcatalog.business.objects.ValidationRule;
import com.contentserv.productcatalog.repositores.ProductCategoryRepository;
import com.contentserv.productcatalog.repositores.ProductModelingRepository;
import com.contentserv.productcatalog.repositores.db.objects.DBProductCategory;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

@Service
public class ProductCategoryService {

  @Autowired
  private ProductCategoryRepository productCategoryRepository;

  @Autowired
  private ProductModelingRepository productModelingRepository;

  public String saveProductCategory(String name, List<ValidationRule> validationRules) {
    DBProductCategory dbProductCategory = productCategoryRepository.findOneByName(name);
    if (dbProductCategory == null) {
      dbProductCategory = new DBProductCategory();
      dbProductCategory.setName(name);
      if (!CollectionUtils.isEmpty(validationRules)) {
        dbProductCategory.setRules(convertObjectToByteArray(validationRules));
      }
      productCategoryRepository.save(dbProductCategory);
    }

    return name;
  }

  private static byte[] convertObjectToByteArray(Object obj) {
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(obj);
      oos.flush();
      return bos.toByteArray();
    } catch (Exception e) {
      throw new RuntimeException("Failed to convert obj to byte array:" + e.getMessage());
    }
  }

  public static List<ValidationRule> convertToListOfValidationRules(byte[] data) {
    try {
      ByteArrayInputStream in = new ByteArrayInputStream(data);
      ObjectInputStream is = new ObjectInputStream(in);
      return (List<ValidationRule>) is.readObject();
    } catch (Exception e) {
      throw new RuntimeException("Failed to convert byte[] to List of validation rules:" + e.getMessage());
    }
  }

  public List<ValidationRule> fetchProductCategoryModelingAttributes(String name) {
    DBProductCategory dbProductCategory = productCategoryRepository.findOneByName(name);
    if (dbProductCategory == null) {
      throw new RuntimeException("No product category exists with name:" + name);
    }
    return convertToListOfValidationRules(dbProductCategory.getRules());
  }

  public void deleteProductCategory(String name) {
    DBProductCategory dbProductCategory = productCategoryRepository.findOneByName(name);
    if (dbProductCategory != null) {
      productCategoryRepository.delete(dbProductCategory);
    }
  }
}