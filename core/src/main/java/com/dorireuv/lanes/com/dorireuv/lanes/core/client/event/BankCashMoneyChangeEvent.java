package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

@SuppressWarnings("UnusedDeclaration")
public class BankCashMoneyChangeEvent implements Event {

  private final int bankCashMoney;

  public BankCashMoneyChangeEvent(int bankCashMoney) {
    this.bankCashMoney = bankCashMoney;
  }

  public int getBankCashMoney() {
    return bankCashMoney;
  }
}
