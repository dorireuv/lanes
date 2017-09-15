package com.dorireuv.lanes.com.dorireuv.lanes.core.data.event;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class PredefinedEventLoaderTest {
  @Test
  public void testLoad() throws Exception {
    PredefinedEventLoader predefinedEventLoader = new PredefinedEventLoader();
    List<GalacticBombEventDefinition> galacticBombEventDefinitionList =
        predefinedEventLoader.load();
    assertTrue(galacticBombEventDefinitionList.size() > 0);
  }
}
