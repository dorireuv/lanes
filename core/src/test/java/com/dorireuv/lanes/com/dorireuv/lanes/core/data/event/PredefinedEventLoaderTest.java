package com.dorireuv.lanes.com.dorireuv.lanes.core.data.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class PredefinedEventLoaderTest extends TestBase {
  @Test
  public void testLoad() throws Exception {
    PredefinedEventLoader predefinedEventLoader = new PredefinedEventLoader();
    List<GalacticBombEventDefinition> galacticBombEventDefinitionList =
        predefinedEventLoader.load();
    assertTrue(galacticBombEventDefinitionList.size() > 0);
  }
}
