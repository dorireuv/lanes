package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CompanySorterTest {

  private Company company1;
  private Company company2;
  private List<Company> companies;
  private CompanySorter companySorter;

  @Before
  public void setUp() {
    CompanyDefinition companyDefinition1 = CompanyDefinition.create('A', "A");
    CompanyDefinition companyDefinition2 = CompanyDefinition.create('A', "A");
    company1 = new Company(companyDefinition1);
    company2 = new Company(companyDefinition2);
    companies = Arrays.asList(company1, company2);
    companySorter = new CompanySorter();
  }

  @Test
  public void testSort() throws Exception {
    company1.setSize(1);
    company2.setSize(2);
    companySorter.sort(companies);
    Assert.assertEquals(companies, Arrays.asList(company2, company1));
  }
}
