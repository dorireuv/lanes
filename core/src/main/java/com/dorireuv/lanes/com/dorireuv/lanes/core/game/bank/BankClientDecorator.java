package com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.BankCashMoneyChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;

public class BankClientDecorator implements Bank {

  private final Bank bank;
  private final ClientEventSubscriber clientEventSubscriber;

  public BankClientDecorator(Bank bank, ClientEventSubscriber clientEventSubscriber) {
    this.bank = bank;
    this.clientEventSubscriber = clientEventSubscriber;
  }

  @Override
  public int getCashMoney() {
    return bank.getCashMoney();
  }

  @Override
  public void addCashMoney(int amount) {
    if (amount != 0) {
      bank.addCashMoney(amount);
      clientEventSubscriber.onBankCashMoneyChange(
          new BankCashMoneyChangeEvent(bank.getCashMoney()));
    }
  }
}
