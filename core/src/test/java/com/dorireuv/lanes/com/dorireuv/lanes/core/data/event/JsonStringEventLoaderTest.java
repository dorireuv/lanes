package com.dorireuv.lanes.com.dorireuv.lanes.core.data.event;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.gen5.api.Assertions.assertThrows;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

final class JsonStringEventLoaderTest {

  @Test
  void loadPredefined_always_isNotEmpty() {
    assertThat(JsonStringEventLoader.loadPredefined()).isNotEmpty();
  }

  @Test
  void load_validJsonString_returnsAllEvents() throws Exception {
    String jsonString =
        Joiner.on('\n')
            .join(
                "[",
                "  {",
                "    \"text\": \"A\",",
                "    \"effect\": -0.1",
                "  },",
                "  {",
                "    \"text\": \"B\",",
                "    \"effect\": 0.2",
                "  },",
                "]");

    ImmutableList<GalacticBombEventDefinition> galacticBombEventDefinitions =
        JsonStringEventLoader.load(jsonString);

    assertThat(galacticBombEventDefinitions)
        .containsExactly(
            GalacticBombEventDefinition.create("A", -0.1),
            GalacticBombEventDefinition.create("B", 0.2))
        .inOrder();
  }

  @Test
  void load_invalidJsonString_throwsJSONException() {
    String jsonString = "{";
    assertThrows(JSONException.class, () -> JsonStringEventLoader.load(jsonString));
  }
}
