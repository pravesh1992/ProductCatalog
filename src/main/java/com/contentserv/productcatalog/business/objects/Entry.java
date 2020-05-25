package com.contentserv.productcatalog.business.objects;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Entry {
  private final String key;
  private final String value;

  public Entry() {
    this.key = null;
    this.value = null;
  }

  public Entry(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Entry{" +
      "key='" + key + '\'' +
      ", value='" + value + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Entry)) return false;
    Entry entry = (Entry) o;
    return Objects.equals(getKey(), entry.getKey()) &&
      Objects.equals(getValue(), entry.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), getValue());
  }
}
