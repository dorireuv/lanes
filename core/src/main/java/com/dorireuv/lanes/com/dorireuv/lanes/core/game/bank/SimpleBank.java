package com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank;

import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;

public class SimpleBank implements Bank {

  private int cashMoney;

  public SimpleBank() {
    this(Config.getBankGameStartCashMoney());
  }

  public SimpleBank(int cashMoney) {
    this.cashMoney = cashMoney;
  }

  @Override
  public int getCashMoney() {
    return cashMoney;
  }

  @Override
  public void addCashMoney(int amount) {
    cashMoney += amount;
  }
}
