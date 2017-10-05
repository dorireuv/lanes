package com.dorireuv.lanes.com.dorireuv.lanes.core.data.company;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.gen5.api.Assertions.assertThrows;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

class JsonStringCompanyLoaderTest {

  @Test
  void loadPredefined_always_isNotEmpty() {
    assertThat(JsonStringCompanyLoader.loadPredefined()).isNotEmpty();
  }

  @Test
  void load_validJsonString_returnsAllCompanies() {
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

    ImmutableList<CompanyDefinition> companyDefinitions = JsonStringCompanyLoader.load(jsonString);

    assertThat(companyDefinitions)
        .containsExactly(
            CompanyDefinition.create('A', "A Name"), CompanyDefinition.create('B', "B Name"))
        .inOrder();
  }

  @Test
  void load_invalidJsonString_throwsJSONException() {
    String jsonString = "{";
    assertThrows(JSONException.class, () -> JsonStringCompanyLoader.load(jsonString));
  }
}
