package com.dorireuv.lanes.com.dorireuv.lanes.core.data.company;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class PredefinedCompanyLoaderTest extends TestBase {
  @Test
  public void testLoad() throws Exception {
    PredefinedCompanyLoader predefinedCompanyLoader = new PredefinedCompanyLoader();
    List<CompanyDefinition> companies = predefinedCompanyLoader.load();
    assertTrue(companies.size() > 0);
  }
}
