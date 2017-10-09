package com.dorireuv.lanes.com.dorireuv.lanes.core.turn;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Turn {

  public abstract int getPlayerIndex();

  public abstract int getNumber();

  public static Builder builder() {
    return new AutoValue_Turn.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder setPlayerIndex(int value);

    public abstract Builder setNumber(int value);

    public abstract Turn build();
  }
}
