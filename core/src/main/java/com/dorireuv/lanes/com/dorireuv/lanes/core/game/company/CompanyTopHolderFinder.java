package com.dorireuv.lanes.com.dorireuv.lanes.core.game.company;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import java.util.Collection;

public class CompanyTopHolderFinder {

  private final Company company;
  private final Collection<Player> players;

  public CompanyTopHolderFinder(Company company, Collection<Player> players) {
    this.company = company;
    this.players = players;
  }

  public int find() {
    Player topHolder = null;
    for (Player player : players) {
      int amount = player.getNumOfStocks(company);
      if (amount > 0 && (topHolder == null || topHolder.getNumOfStocks(company) < amount)) {
        topHolder = player;
      }
    }
    if (topHolder == null) {
      return -1;
    }

    return topHolder.getIndex();
  }
}
