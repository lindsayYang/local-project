package com.lindsay.test.dto;

import lombok.Data;

/**
 * EasyPOIModel
 *
 * @author Lindsay
 * @date 2019/9/18 17:33
 **/
@Data
public class EasyPOIModel {

  private int id;

  private String project;

  private String name;

  private String sex;

  private int age;

  public EasyPOIModel(int id, String project, String name, String sex, int age) {
    this.id = id;
    this.project = project;
    this.name = name;
    this.sex = sex;
    this.age = age;
  }

}
