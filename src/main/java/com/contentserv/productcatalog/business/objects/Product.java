package com.contentserv.productcatalog.business.objects;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;

@Component
public class Product implements Serializable {
  private Integer id;
  private String name;
  private String description;
  private String category;
  private Set<String> modelingKeys;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Set<String> getModelingKeys() {
    return modelingKeys;
  }

  public void setModelingKeys(Set<String> modelingKeys) {
    this.modelingKeys = modelingKeys;
  }
}