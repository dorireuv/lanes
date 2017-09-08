package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.Action;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;

public class PlayerCashMoneyInterestChecker extends CheckerBase {

  public PlayerCashMoneyInterestChecker(ActionFactory actionFactory) {
    super(actionFactory);
  }

  @Override
  public Action check() {
    return actionFactory.createPlayerCashMoneyInterestAction();
  }
}
