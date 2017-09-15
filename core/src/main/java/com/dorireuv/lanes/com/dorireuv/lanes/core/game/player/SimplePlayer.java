package com.dorireuv.lanes.com.dorireuv.lanes.core.game.player;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import java.util.HashMap;
import java.util.Map;

public class SimplePlayer implements Player {

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
  public Map<Company, Integer> getHoldings() {
    return holdings;
  }

  @Override
  public int getNumOfStocks(Company company) {
    Integer numOfStocks = holdings.get(company);
    if (numOfStocks == null) {
      return 0;
    }

    return numOfStocks;
  }

  @Override
  public void setNumOfStocks(Company company, int numOfStocks) {
    holdings.put(company, numOfStocks);
  }
}
