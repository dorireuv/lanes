package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.data;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;

public class CompanyData extends DataBase {

  private final CompanyDefinition companyDefinition;

  public CompanyData(CompanyDefinition companyDefinition) {
    this.companyDefinition = companyDefinition;
  }

  public CompanyDefinition getCompanyDefinition() {
    return companyDefinition;
  }

  public boolean equals(Object other) {
    if (!super.equals(other)) {
      return false;
    }

    return getCompanyDefinition().equals(((CompanyData) other).getCompanyDefinition());
  }

  public int hashCode() {
    return getClass().hashCode();
  }
}
