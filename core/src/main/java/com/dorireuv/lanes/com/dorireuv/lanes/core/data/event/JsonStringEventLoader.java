package com.dorireuv.lanes.com.dorireuv.lanes.core.data.event;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JsonStringEventLoader {

  public static ImmutableList<GalacticBombEventDefinition> loadPredefined() {
    return load(readJsonFileToString());
  }

  private static String readJsonFileToString() {
    try {
      return Resources.toString(
          JsonStringEventLoader.class.getResource("/events.json"), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new UncheckedIOException("Failed to read file, cannot recover", e);
    }
  }

  /** @throws JSONException when given json string has an invalid structure */
  @VisibleForTesting
  static ImmutableList<GalacticBombEventDefinition> load(String jsonString) {
    return parseEvents(new JSONArray(jsonString));
  }

  private static ImmutableList<GalacticBombEventDefinition> parseEvents(JSONArray jsonArray) {
    ImmutableList.Builder<GalacticBombEventDefinition> companyDefinitions = ImmutableList.builder();
    for (int i = 0; i < jsonArray.length(); i++) {
      companyDefinitions.add(parseEvent(jsonArray.getJSONObject(i)));
    }

    return companyDefinitions.build();
  }

  private static GalacticBombEventDefinition parseEvent(JSONObject jsonObject) {
    return GalacticBombEventDefinition.create(
        jsonObject.getString("text"), jsonObject.getDouble("effect"));
  }
}
