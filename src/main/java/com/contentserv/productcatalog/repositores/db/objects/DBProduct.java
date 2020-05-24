package com.contentserv.productcatalog.repositores.db.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class DBProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "product_id")
  private Integer id;

  @Column(name = "product_name")
  private String name;

  @Column(name = "product_description")
  private String description;

  @Column(name = "modeling_keys", columnDefinition = "MEDIUMTEXT")
  private String modelingKeys;

  @ManyToOne(optional = true)
  @JoinColumn(name = "product_category_id")
  private DBProductCategory dbProductCategory;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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

  public DBProductCategory getDbProductCategory() {
    return dbProductCategory;
  }

  public void setDbProductCategory(DBProductCategory dbProductCategory) {
    this.dbProductCategory = dbProductCategory;
  }

  public String getModelingKeys() {
    return modelingKeys;
  }

  public void setModelingKeys(String modelingKeys) {
    this.modelingKeys = modelingKeys;
  }
}