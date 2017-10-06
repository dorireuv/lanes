package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.FreezeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.turn.TurnIterator;

final class FreezeAction extends ActionBase {

  private final TurnIterator turnIterator;

  FreezeAction(ClientEventSubscriber clientEventSubscriber, TurnIterator turnIterator) {
    super(clientEventSubscriber);
    this.turnIterator = turnIterator;
  }

  @Override
  public void doAction() {
    turnIterator.setShouldEndTurn();
    // nothing to do
    clientEventSubscriber.onFreeze(new FreezeEvent());
  }
}
