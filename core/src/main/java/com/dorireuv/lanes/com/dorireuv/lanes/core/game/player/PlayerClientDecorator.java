package com.dorireuv.lanes.com.dorireuv.lanes.core.game.player;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.PlayerCashMoneyChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.PlayerNetValueChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;

import java.util.Map;

public class PlayerClientDecorator implements Player {

  private final Player player;
  private final ClientEventSubscriber clientEventSubscriber;

  public PlayerClientDecorator(Player player, ClientEventSubscriber clientEventSubscriber) {
    this.player = player;
    this.clientEventSubscriber = clientEventSubscriber;
  }

  @Override
  public int getIndex() {
    return player.getIndex();
  }

  @Override
  public String getName() {
    return player.getName();
  }

  @Override
  public int getCashMoney() {
    return player.getCashMoney();
  }

  @Override
  public void setCashMoney(int cashMoney) {
    int curCashMoney = getCashMoney();
    if (curCashMoney != cashMoney) {
      player.setCashMoney(cashMoney);
      clientEventSubscriber.onPlayerCashMoneyChange(
          new PlayerCashMoneyChangeEvent(getIndex(), cashMoney));
      clientEventSubscriber.onPlayerNetValueChange(
          new PlayerNetValueChangeEvent(getIndex(), new PlayerNetValueCalculator(player).calc()));
    }
  }

  @Override
  public Map<Company, Integer> getHoldings() {
    return player.getHoldings();
  }

  @Override
  public int getNumOfStocks(Company company) {
    return player.getNumOfStocks(company);
  }

  @Override
  public void setNumOfStocks(Company company, int numOfStocks) {
    player.setNumOfStocks(company, numOfStocks);
  }
}
