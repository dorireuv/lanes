package com.dorireuv.lanes.com.dorireuv.lanes.core.data.company;

import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;

public class PredefinedCompanyLoaderTest {
  @Test
  public void testLoad() throws Exception {
    PredefinedCompanyLoader predefinedCompanyLoader = new PredefinedCompanyLoader();
    List<CompanyDefinition> companies = predefinedCompanyLoader.load();
    assertTrue(companies.size() > 0);
  }
}
