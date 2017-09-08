package com.dorireuv.lanes.com.dorireuv.lanes.core.data.event;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GalacticBombEventDefinition {

  public static GalacticBombEventDefinition create(String text, Double effect) {
    return new AutoValue_GalacticBombEventDefinition(text, effect);
  }

  public abstract String getText();

  public abstract Double getEffect();
}
