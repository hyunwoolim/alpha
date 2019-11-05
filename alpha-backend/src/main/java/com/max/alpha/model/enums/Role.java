package com.max.alpha.model.enums;

public enum Role {
  ADMIN ("ADMIN"), USER1 ("USER1");

  private String value;

  Role(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}

