package com.dorireuv.lanes.com.dorireuv.lanes.core.game.player;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import java.util.Map;

class PlayerNetValueCalculator {

  private final ImmutablePlayer player;

  PlayerNetValueCalculator(ImmutablePlayer player) {
    this.player = player;
  }

  int calc() {
    return calcStockValue() + player.getCashMoney();
  }

  private int calcStockValue() {
    int stockValue = 0;
    for (Map.Entry<Company, Integer> holding : player.getHoldings().entrySet()) {
      Company company = holding.getKey();
      Integer numOfStocks = holding.getValue();
      stockValue += company.getValue() * numOfStocks;
    }

    return stockValue;
  }
}
