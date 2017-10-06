package com.dorireuv.lanes.com.dorireuv.lanes.core.game.player;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableMap;

@AutoValue
public abstract class ImmutablePlayer {

  public abstract int getIndex();

  public abstract String getName();

  public abstract int getCashMoney();

  public abstract ImmutableMap<Company, Integer> getHoldings();

  public final int getNumOfStocks(Company company) {
    return getHoldings().getOrDefault(company, 0);
  }

  public final int getNetValue() {
    return new PlayerNetValueCalculator(this).calc();
  }

  public static Builder builder() {
    return new AutoValue_ImmutablePlayer.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder setIndex(int newIndex);

    public abstract Builder setName(String newName);

    public abstract Builder setCashMoney(int newCashMoney);

    public abstract Builder setHoldings(ImmutableMap<Company, Integer> newHoldings);

    public abstract ImmutablePlayer build();
  }
}
