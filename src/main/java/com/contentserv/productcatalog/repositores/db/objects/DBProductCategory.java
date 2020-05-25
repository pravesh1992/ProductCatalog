package com.contentserv.productcatalog.repositores.db.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_category")
public class DBProductCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "product_category_id")
  private Integer id;

  @Column(name = "product_category_name", unique = true)
  private String name;

  @Column(name = "DATA", unique = false, nullable = false, length = 100000)
  private byte[] rules;

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

  public byte[] getRules() {
    return rules;
  }

  public void setRules(byte[] rules) {
    this.rules = rules;
  }
}