package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.DoublePaymentEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;

class DoublePaymentAction extends ActionBase {

  private final Player player;

  public DoublePaymentAction(ClientEventSubscriber clientEventSubscriber, Player player) {
    super(clientEventSubscriber);
    this.player = player;
  }

  @Override
  public void doAction() {
    player.setCashMoney(player.getCashMoney() * 2);
    clientEventSubscriber.onDoublePayment(new DoublePaymentEvent());
  }
}
