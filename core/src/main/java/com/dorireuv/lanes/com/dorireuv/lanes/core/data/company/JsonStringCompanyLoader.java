package com.dorireuv.lanes.com.dorireuv.lanes.core.data.company;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JsonStringCompanyLoader {

  private JsonStringCompanyLoader() {}

  public static ImmutableList<CompanyDefinition> loadPredefined() {
    return load(readJsonFileToString());
  }

  private static String readJsonFileToString() {
    try {
      return Resources.toString(
          JsonStringCompanyLoader.class.getResource("/companies.json"), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new UncheckedIOException("Failed to read file, cannot recover", e);
    }
  }

  /** @throws JSONException when given json string has an invalid structure */
  @VisibleForTesting
  static ImmutableList<CompanyDefinition> load(String jsonString) {
    return parseCompanyDefinitions(new JSONArray(jsonString));
  }

  private static ImmutableList<CompanyDefinition> parseCompanyDefinitions(JSONArray jsonArray) {
    ImmutableList.Builder<CompanyDefinition> companyDefinitions = ImmutableList.builder();
    for (int i = 0; i < jsonArray.length(); i++) {
      companyDefinitions.add(parseCompanyDefinition(jsonArray.getJSONObject(i)));
    }

    return companyDefinitions.build();
  }

  private static CompanyDefinition parseCompanyDefinition(JSONObject jsonObject) {
    return CompanyDefinition.create(
        jsonObject.getString("symbol").charAt(0), jsonObject.getString("name"));
  }
}
