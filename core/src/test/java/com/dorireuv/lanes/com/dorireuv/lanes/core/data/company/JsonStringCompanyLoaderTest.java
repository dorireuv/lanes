package com.dorireuv.lanes.com.dorireuv.lanes.core.data.company;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.collection.iterable.IterablePair;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.collection.iterable.Pair;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader.FailedToLoadException;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.gen5.api.Assertions;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonStringCompanyLoaderTest extends TestBase {
  @Test
  public void testLoad() throws Exception {
    // create json string
    List<CompanyDefinition> expectedCompanies = new LinkedList<>();
    expectedCompanies.add(CompanyDefinition.create('A', "A Hello"));
    expectedCompanies.add(CompanyDefinition.create('B', "B Hello"));
    Gson gson = new Gson();
    String jsonString = gson.toJson(expectedCompanies);

    // load json string
    JsonStringCompanyLoader companyLoader = new JsonStringCompanyLoader(jsonString);
    List<CompanyDefinition> actualCompanies = companyLoader.load();

    // compare
    assertEquals(expectedCompanies.size(), actualCompanies.size());
    IterablePair<CompanyDefinition, CompanyDefinition> companyPairs =
        new IterablePair<>(expectedCompanies, actualCompanies);
    for (Pair<CompanyDefinition, CompanyDefinition> pair : companyPairs) {
      CompanyDefinition expectedCompanyDefinition = pair.first();
      CompanyDefinition actualCompanyDefinition = pair.second();
      assertEquals(expectedCompanyDefinition.getSymbol(), actualCompanyDefinition.getSymbol());
      assertEquals(expectedCompanyDefinition.getName(), actualCompanyDefinition.getName());
    }
  }

  @Test
  public void testLoadWithInvalidJsonThrowsException() throws Exception {
    String invalidJsonString = "{";
    JsonStringCompanyLoader companyLoader = new JsonStringCompanyLoader(invalidJsonString);
    Assertions.assertThrows(FailedToLoadException.class, companyLoader::load);
  }
}
