package com.contentserv.productcatalog.business.objects;

import com.contentserv.productcatalog.repositores.db.objects.Operators;

import java.io.Serializable;

public class ValidationRule implements Serializable {
  private String key;
  private Object value;
  private String valueType;
  private String errorMessage;
  private Operators operator;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public String getValueType() {
    return valueType;
  }

  public void setValueType(String valueType) {
    this.valueType = valueType;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public Operators getOperator() {
    return operator;
  }

  public void setOperator(Operators operator) {
    this.operator = operator;
  }
}
