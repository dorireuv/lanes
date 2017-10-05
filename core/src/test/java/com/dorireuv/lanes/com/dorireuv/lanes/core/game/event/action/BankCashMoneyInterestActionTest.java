package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.SimpleBank;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class BankCashMoneyInterestActionTest {
  @Test
  void check() throws Exception {
    SimpleBank bank = new SimpleBank(2000);
    BankCashMoneyInterestAction bankCashMoneyAction = new BankCashMoneyInterestAction(bank);
    bankCashMoneyAction.doAction();
    Assert.assertEquals(bank.getCashMoney(), 2100);
  }
}
