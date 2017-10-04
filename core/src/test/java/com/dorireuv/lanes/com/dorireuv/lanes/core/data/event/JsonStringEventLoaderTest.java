package com.dorireuv.lanes.com.dorireuv.lanes.core.data.event;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.gen5.api.Assertions.assertThrows;

import com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader.FailedToLoadException;
import com.google.common.base.Joiner;
import java.util.List;
import org.junit.Test;

public class JsonStringEventLoaderTest {

  @Test
  public void testLoad_validJsonString_returnsAllEvents() throws Exception {
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

    JsonStringEventLoader eventLoader = new JsonStringEventLoader(jsonString);
    List<GalacticBombEventDefinition> galacticBombEventDefinitions = eventLoader.load();

    assertThat(galacticBombEventDefinitions)
        .containsExactly(
            GalacticBombEventDefinition.create("A", -0.1),
            GalacticBombEventDefinition.create("B", 0.2))
        .inOrder();
  }

  @Test
  public void testLoad_invalidJsonString_throwsFailedToLoadException() {
    String invalidJsonString = "{";
    JsonStringEventLoader eventLoader = new JsonStringEventLoader(invalidJsonString);
    assertThrows(FailedToLoadException.class, eventLoader::load);
  }
}
