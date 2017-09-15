package com.dorireuv.lanes.com.dorireuv.lanes.core.game.player;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import java.util.Map;

public class PlayerStockValueCalculator {
  private final Player player;

  public PlayerStockValueCalculator(Player player) {
    this.player = player;
  }

  public int calc() {
    int netValue = 0;

    for (Map.Entry<Company, Integer> holding : player.getHoldings().entrySet()) {
      Company company = holding.getKey();
      Integer numOfStocks = holding.getValue();
      netValue += company.getValue() * numOfStocks;
    }

    return netValue;
  }
}
