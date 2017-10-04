package com.dorireuv.lanes.com.dorireuv.lanes.core.game.company;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;

public class Company {

  private CompanyDefinition companyDefinition;
  private int size;
  private int value;

  public Company(CompanyDefinition companyDefinition) {
    this(companyDefinition, 0, 0);
  }

  public Company(CompanyDefinition companyDefinition, int size, int value) {
    this.setCompanyDefinition(companyDefinition);
    this.setSize(size);
    this.setValue(value);
  }

  public CompanyDefinition getCompanyDefinition() {
    return companyDefinition;
  }

  private void setCompanyDefinition(CompanyDefinition companyDefinition) {
    this.companyDefinition = companyDefinition;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public void incSize() {
    size += 1;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public void incValue(int amount) {
    value += amount;
  }
}
