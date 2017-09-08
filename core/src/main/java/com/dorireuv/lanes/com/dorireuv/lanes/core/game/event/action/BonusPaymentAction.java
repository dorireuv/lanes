package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.BonusPaymentEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.Bank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;

class BonusPaymentAction extends ActionBase {

  private final Bank bank;
  private final Player player;

  public BonusPaymentAction(ClientEventSubscriber clientEventSubscriber, Bank bank, Player player) {
    super(clientEventSubscriber);
    this.bank = bank;
    this.player = player;
  }

  @Override
  public void doAction() {
    int bonusPayment = (int) (bank.getCashMoney() * Config.getBonusPaymentPercentage());
    bank.addCashMoney(-bonusPayment);
    player.setCashMoney(player.getCashMoney() + bonusPayment);
    clientEventSubscriber.onBonusPayment(new BonusPaymentEvent(bonusPayment));
  }
}
