package com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank;

public class SimpleBank implements Bank {

  private int cashMoney;

  public SimpleBank() {
    this(0);
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
