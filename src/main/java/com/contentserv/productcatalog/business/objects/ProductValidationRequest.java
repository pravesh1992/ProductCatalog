package com.contentserv.productcatalog.business.objects;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductValidationRequest {
  private String category;
  List<ValidationRule> validationRules;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public List<ValidationRule> getValidationRules() {
    return validationRules;
  }

  public void setValidationRules(List<ValidationRule> validationRules) {
    this.validationRules = validationRules;
  }
}