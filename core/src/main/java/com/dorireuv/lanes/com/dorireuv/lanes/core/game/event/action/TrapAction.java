package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.TrapEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.Bank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.turn.TurnIterator;

class TrapAction extends ActionBase {

  private final TurnIterator turnIterator;
  private final Bank bank;
  private final Player player;

  public TrapAction(
      ClientEventSubscriber clientEventSubscriber,
      TurnIterator turnIterator,
      Bank bank,
      Player player) {
    super(clientEventSubscriber);
    this.turnIterator = turnIterator;
    this.bank = bank;
    this.player = player;
  }

  @Override
  public void doAction() {
    int amount = player.getCashMoney();
    player.setCashMoney(0);
    bank.addCashMoney(amount);
    clientEventSubscriber.onTrap(new TrapEvent());
    turnIterator.setShouldEndTurn();
  }
}
