package com.dorireuv.lanes.com.dorireuv.lanes.core.data.company;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class PredefinedCompanyLoaderTest {
  @Test
  public void testLoad() throws Exception {
    PredefinedCompanyLoader predefinedCompanyLoader = new PredefinedCompanyLoader();
    List<CompanyDefinition> companies = predefinedCompanyLoader.load();
    assertTrue(companies.size() > 0);
  }
}
