package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningResultRange implements java.io.Serializable {

  private Long id;
  private int version;
  private int upperRange;
  private String grade;

  public ElearningResultRange() {
  }

  public ElearningResultRange(int upperRange, String grade) {
    this.upperRange = upperRange;
    this.grade = grade;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getVersion() {
    return this.version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public int getUpperRange() {
    return this.upperRange;
  }

  public void setUpperRange(int upperRange) {
    this.upperRange = upperRange;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

}
