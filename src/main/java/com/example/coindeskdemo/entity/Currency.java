package com.example.coindeskdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Currency {

  @Id
  private String code;

  @Column(name = "name_zh")
  private String nameZh;

  public Currency() {}

  public Currency(String code, String nameZh) {
    this.code = code;
    this.nameZh = nameZh;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getNameZh() {
    return nameZh;
  }

  public void setNameZh(String nameZh) {
    this.nameZh = nameZh;
  }
}
