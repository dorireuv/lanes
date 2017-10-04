package com.dorireuv.lanes.com.dorireuv.lanes.core.data.company;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.gen5.api.Assertions.assertThrows;

import com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader.FailedToLoadException;
import com.google.common.base.Joiner;
import java.util.List;
import org.junit.Test;

public class JsonStringCompanyLoaderTest {

  @Test
  public void testLoad_validJsonString_returnsAllCompanies() throws Exception {
    String jsonString =
        Joiner.on('\n')
            .join(
                "[",
                "  {",
                "    \"symbol\": \"A\",",
                "    \"name\": \"A Name\"",
                "  },",
                "  {",
                "    \"symbol\": \"B\",",
                "    \"name\": \"B Name\"",
                "  },",
                "]");

    JsonStringCompanyLoader companyLoader = new JsonStringCompanyLoader(jsonString);
    List<CompanyDefinition> companyDefinitions = companyLoader.load();

    assertThat(companyDefinitions)
        .containsExactly(
            CompanyDefinition.create('A', "A Name"), CompanyDefinition.create('B', "B Name"))
        .inOrder();
  }

  @Test
  public void testLoad_invalidJsonString_throwsFailedToLoadException() {
    String invalidJsonString = "{";
    JsonStringCompanyLoader companyLoader = new JsonStringCompanyLoader(invalidJsonString);
    assertThrows(FailedToLoadException.class, companyLoader::load);
  }
}
