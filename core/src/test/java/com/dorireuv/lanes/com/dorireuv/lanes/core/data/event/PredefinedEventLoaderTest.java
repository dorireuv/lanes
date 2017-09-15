package com.dorireuv.lanes.com.dorireuv.lanes.core.data.event;

import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;

public class PredefinedEventLoaderTest {
  @Test
  public void testLoad() throws Exception {
    PredefinedEventLoader predefinedEventLoader = new PredefinedEventLoader();
    List<GalacticBombEventDefinition> galacticBombEventDefinitionList =
        predefinedEventLoader.load();
    assertTrue(galacticBombEventDefinitionList.size() > 0);
  }
}
