package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.Action;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;

public class BonusPaymentChecker extends CheckerBase {

  private final RandomWrapper randomWrapper;

  public BonusPaymentChecker(ActionFactory actionFactory, RandomWrapper randomWrapper) {
    super(actionFactory);
    this.randomWrapper = randomWrapper;
  }

  @Override
  public Action check() {
    if (randomWrapper.nextDouble() < Config.getBonusPaymentChance()) {
      return actionFactory.createBonusPaymentAction();
    }

    return actionFactory.createNullAction();
  }
}
