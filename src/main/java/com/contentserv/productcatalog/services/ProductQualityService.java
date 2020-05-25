package com.contentserv.productcatalog.services;

import com.contentserv.productcatalog.business.objects.Entry;
import com.contentserv.productcatalog.business.objects.Product;
import com.contentserv.productcatalog.business.objects.ValidationRule;
import com.contentserv.productcatalog.repositores.ProductCategoryRepository;
import com.contentserv.productcatalog.repositores.ProductRepository;
import com.contentserv.productcatalog.repositores.db.objects.DBProduct;
import com.contentserv.productcatalog.repositores.db.objects.DBProductCategory;
import com.contentserv.productcatalog.repositores.db.objects.Operators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductQualityService {

//  @Autowired
//  private ValidationRuleRepository validationRuleRepository;

  @Autowired
  private ProductCategoryRepository productCategoryRepository;

  @Autowired
  private ProductRepository productRepository;

  public String validateProductQuality(Integer id) {
    Optional<DBProduct> optionalDBProduct = productRepository.findById(id);
    if (!optionalDBProduct.isPresent()) {
      throw new RuntimeException("No Product found with id:" + id);
    }
    DBProductCategory productCategory = productCategoryRepository.findOneByName(optionalDBProduct.get().getDbProductCategory().getName());
    if (productCategory == null)
      throw new RuntimeException("No product category exists:" + optionalDBProduct.get().getDbProductCategory().getName());
    List<ValidationRule> validationRules = ProductCategoryService.convertToListOfValidationRules(productCategory.getRules());
    Product product = ProductService.convert(optionalDBProduct.get());
    try {
      for (ValidationRule validationRule : validationRules) {
        validateRule(validationRule, product.getModelingKeys());
      }
    } catch (Exception e) {
      return "Not good, Reason:" + e.getMessage();
    }
    return "Good";
  }

  public void validateRule(ValidationRule validationRule, Set<Entry> productMetaKeys) {
    for (Entry entry : productMetaKeys) {
      if (entry.getKey().equalsIgnoreCase(validationRule.getKey())) {
        boolean result = false;
        if (validationRule.getOperator() == Operators.EQ) {
          result = isValuesEquals(validationRule.getValue(), validationRule.getValueType(), entry.getValue());
        } else if (validationRule.getOperator() == Operators.NE) {
          result = isValuesNotEquals(validationRule.getValue(), validationRule.getValueType(), entry.getValue());
        } else if (validationRule.getOperator() == Operators.GE) {
          result = isValuesGreaterEquals(validationRule.getValue(), validationRule.getValueType(), entry.getValue());
        } else if (validationRule.getOperator() == Operators.LE) {
          result = isValuesLessEquals(validationRule.getValue(), validationRule.getValueType(), entry.getValue());
        }
        if (!result) {
          throw new RuntimeException("Product is not good quality, because " + entry.getKey() + " is  " + validationRule.getOperator().toString() + " to " + entry.getValue());
        }
      }
    }
  }

  boolean isValuesEquals(Object value, String classType, String productValue) {
    if (classType.equals("java.lang.String")) {
      return productValue.equals(value.toString());
    } else if (classType.equals("java.lang.Integer")) {
      return Integer.valueOf(productValue).equals(value);
    } else if (classType.equals("java.lang.Long")) {
      return Long.valueOf(productValue).equals(value);
    } else if (classType.equals("java.lang.Double")) {
      return Double.valueOf(productValue).equals(value);
    } else if (classType.equals("java.lang.Float")) {
      return Float.valueOf(productValue).equals(value);
    }
    return false;
  }

  boolean isValuesNotEquals(Object value, String classType, String productValue) {
    if (classType.equals("java.lang.String")) {
      return !productValue.equals(value.toString());
    } else if (classType.equals("java.lang.Integer")) {
      return !Integer.valueOf(productValue).equals(value);
    } else if (classType.equals("java.lang.Long")) {
      return !Long.valueOf(productValue).equals(value);
    } else if (classType.equals("java.lang.Double")) {
      return !Double.valueOf(productValue).equals(value);
    } else if (classType.equals("java.lang.Float")) {
      return !Float.valueOf(productValue).equals(value);
    }
    return false;
  }

  boolean isValuesGreaterEquals(Object value, String classType, String productValue) {
    if (classType.equals("java.lang.Integer")) {
      return Integer.valueOf(productValue) > (Integer) value;
    } else if (classType.equals("java.lang.Long")) {
      return Long.valueOf(productValue) > (Long) value;
    } else if (classType.equals("java.lang.Double")) {
      return Double.valueOf(productValue) > (Double) value;
    } else if (classType.equals("java.lang.Float")) {
      return Float.valueOf(productValue) > (Float) value;
    }
    return false;
  }

  boolean isValuesLessEquals(Object value, String classType, String productValue) {
    if (classType.equals("java.lang.Integer")) {
      return Integer.valueOf(productValue) < (Integer) value;
    } else if (classType.equals("java.lang.Long")) {
      return Long.valueOf(productValue) < (Long) value;
    } else if (classType.equals("java.lang.Double")) {
      return Double.valueOf(productValue) < (Double) value;
    } else if (classType.equals("java.lang.Float")) {
      return Float.valueOf(productValue) < (Float) value;
    }
    return false;
  }
}