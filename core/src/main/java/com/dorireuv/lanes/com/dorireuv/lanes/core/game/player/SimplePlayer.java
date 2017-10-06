package com.dorireuv.lanes.com.dorireuv.lanes.core.game.player;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;

public class SimplePlayer extends Player {

  private final int index;
  private final String name;
  private int cashMoney;
  private final Map<Company, Integer> holdings;

  public SimplePlayer(int index, String name, int cashMoney) {
    this.index = index;
    this.name = name;
    this.cashMoney = cashMoney;
    this.holdings = new HashMap<>();
  }

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getCashMoney() {
    return cashMoney;
  }

  @Override
  public void setCashMoney(int cashMoney) {
    this.cashMoney = cashMoney;
  }

  @Override
  public ImmutableMap<Company, Integer> getHoldings() {
    return ImmutableMap.copyOf(holdings);
  }

  @Override
  public void setNumOfStocks(Company company, int numOfStocks) {
    holdings.put(company, numOfStocks);
  }

  @Override
  public ImmutablePlayer immutableCopy() {
    return ImmutablePlayer.builder()
        .setIndex(SimplePlayer.this.getIndex())
        .setName(SimplePlayer.this.getName())
        .setCashMoney(SimplePlayer.this.getCashMoney())
        .setHoldings(SimplePlayer.this.getHoldings())
        .build();
  }
}
