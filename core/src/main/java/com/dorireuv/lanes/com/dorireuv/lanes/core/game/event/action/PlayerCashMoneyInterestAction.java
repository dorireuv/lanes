package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import java.util.Collection;

final class PlayerCashMoneyInterestAction implements Action {

  private final Player player;
  private final Collection<Company> companies;

  PlayerCashMoneyInterestAction(Player player, Collection<Company> companies) {
    this.player = player;
    this.companies = companies;
  }

  @Override
  public void doAction() {
    int totalMoney = 0;
    for (Company company : companies) {
      int numOfStocks = player.getNumOfStocks(company);
      int value = company.getValue();
      totalMoney += (int) (.05 * numOfStocks * value);
    }
    player.setCashMoney(player.getCashMoney() + totalMoney);
  }
}
