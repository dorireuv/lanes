package com.dorireuv.lanes.com.dorireuv.lanes.core.data.event;

import static org.junit.Assert.assertEquals;
import static org.junit.gen5.api.Assertions.assertThrows;

import com.dorireuv.lanes.com.dorireuv.lanes.core.util.collection.iterable.IterablePair;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.collection.iterable.Pair;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader.FailedToLoadException;
import com.google.gson.Gson;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class JsonStringEventLoaderTest {
  @Test
  public void testLoad() throws Exception {
    // create json string
    List<GalacticBombEventDefinition> expectedGalacticBombEventDefinitionList = new LinkedList<>();
    expectedGalacticBombEventDefinitionList.add(GalacticBombEventDefinition.create("A", 0.1));
    expectedGalacticBombEventDefinitionList.add(GalacticBombEventDefinition.create("B", -0.1));
    Gson gson = new Gson();
    String jsonString = gson.toJson(expectedGalacticBombEventDefinitionList);

    // load json string
    JsonStringEventLoader eventLoader = new JsonStringEventLoader(jsonString);
    List<GalacticBombEventDefinition> actualGalacticBombEventDefinitionList = eventLoader.load();

    // compare
    assertEquals(
        expectedGalacticBombEventDefinitionList.size(),
        actualGalacticBombEventDefinitionList.size());
    IterablePair<GalacticBombEventDefinition, GalacticBombEventDefinition> eventPairs =
        new IterablePair<>(
            expectedGalacticBombEventDefinitionList, actualGalacticBombEventDefinitionList);
    for (Pair<GalacticBombEventDefinition, GalacticBombEventDefinition> pair : eventPairs) {
      GalacticBombEventDefinition expectedGalacticBombEventDefinition = pair.first();
      GalacticBombEventDefinition actualGalacticBombEventDefinition = pair.first();
      assertEquals(expectedGalacticBombEventDefinition, actualGalacticBombEventDefinition);
    }
  }

  @Test
  public void testLoadWithInvalidJsonThrowsException() throws Exception {
    String invalidJsonString = "{";
    JsonStringEventLoader eventLoader = new JsonStringEventLoader(invalidJsonString);
    assertThrows(FailedToLoadException.class, eventLoader::load);
  }
}
