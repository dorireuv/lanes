package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;

public class CreateCompanyEvent implements Event {

  private final CompanyDefinition companyDefinition;

  public CreateCompanyEvent(CompanyDefinition companyDefinition) {
    this.companyDefinition = companyDefinition;
  }

  public CompanyDefinition getCompanyDefinition() {
    return companyDefinition;
  }
}
