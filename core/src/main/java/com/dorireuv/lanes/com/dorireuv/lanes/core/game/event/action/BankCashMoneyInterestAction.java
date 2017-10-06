package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.Bank;

final class BankCashMoneyInterestAction implements Action {

  private final Bank bank;

  BankCashMoneyInterestAction(Bank bank) {
    this.bank = bank;
  }

  @Override
  public void doAction() {
    bank.addCashMoney((int) (bank.getCashMoney() * Config.getBankCashInterest()));
  }
}
