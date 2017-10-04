package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

public class PlayerCashMoneyChangeEvent implements Event {

  private final int playerIndex;
  private final int cashMoney;

  public PlayerCashMoneyChangeEvent(int playerIndex, int cashMoney) {
    this.playerIndex = playerIndex;
    this.cashMoney = cashMoney;
  }

  public int getPlayerIndex() {
    return playerIndex;
  }

  public int getCashMoney() {
    return cashMoney;
  }
}
