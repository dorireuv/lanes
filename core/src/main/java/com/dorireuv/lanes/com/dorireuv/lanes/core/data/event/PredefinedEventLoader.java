package com.dorireuv.lanes.com.dorireuv.lanes.core.data.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader.PredefinedListLoader;
import java.util.List;

public class PredefinedEventLoader extends PredefinedListLoader<List<GalacticBombEventDefinition>> {

  public PredefinedEventLoader() {
    super(
        JsonStringEventLoader::new,
        PredefinedEventLoader.class.getResourceAsStream("/events.json"));
  }
}
