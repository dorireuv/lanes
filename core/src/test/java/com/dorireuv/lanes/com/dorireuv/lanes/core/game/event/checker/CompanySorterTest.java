package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import static com.google.common.truth.Truth.assertThat;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

final class CompanySorterTest {

  private static final CompanyDefinition COMPANY_DEFINITION_1 = CompanyDefinition.create('A', "A");
  private static final CompanyDefinition COMPANY_DEFINITION_2 = CompanyDefinition.create('B', "B");

  @Test
  void sort() throws Exception {
    Company company1 = new Company(COMPANY_DEFINITION_1);
    company1.setSize(1);
    Company company2 = new Company(COMPANY_DEFINITION_2);
    company2.setSize(2);
    List<Company> companies = Arrays.asList(company1, company2);

    CompanySorter.sort(companies);

    assertThat(companies).containsExactly(company2, company1).inOrder();
  }
}
