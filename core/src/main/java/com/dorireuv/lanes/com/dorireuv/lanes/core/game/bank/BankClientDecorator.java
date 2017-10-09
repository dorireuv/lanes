package com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.BankCashMoneyChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import javax.inject.Inject;

public class BankClientDecorator implements Bank {

  private final Bank bank;
  private final ClientEventSubscriber clientEventSubscriber;

  @Inject
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
    bank.addCashMoney(amount);
    if (amount != 0) {
      clientEventSubscriber.onBankCashMoneyChange(
          new BankCashMoneyChangeEvent(bank.getCashMoney()));
    }
  }
}
