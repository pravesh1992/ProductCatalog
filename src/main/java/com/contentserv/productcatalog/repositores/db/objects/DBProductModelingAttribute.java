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
@Table(name = "product_modeling_attributes")
public class DBProductModelingAttribute {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "name")
  private String name;

  @ManyToOne(optional = true)
  @JoinColumn(name = "product_category_id")
  DBProductCategory dbProductCategory;

  DBProductModelingAttribute() {
  }

  public DBProductModelingAttribute(String name, DBProductCategory dbProductCategory) {
    this.name = name;
    this.dbProductCategory = dbProductCategory;
  }

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

  public DBProductCategory getDbProductCategory() {
    return dbProductCategory;
  }

  public void setDbProductCategory(DBProductCategory dbProductCategory) {
    this.dbProductCategory = dbProductCategory;
  }
}