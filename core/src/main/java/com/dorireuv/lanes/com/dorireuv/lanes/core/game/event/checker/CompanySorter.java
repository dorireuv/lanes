package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;

import java.util.List;

class CompanySorter {
  public void sort(List<Company> companies) {
    companies.sort((company1, company2) -> {
      if (company1.getSize() != company2.getSize()) {
        return company2.getSize() - company1.getSize();
      }

      return company2.getCompanyDefinition().getSymbol()
          - company1.getCompanyDefinition().getSymbol();
    });
  }
}
