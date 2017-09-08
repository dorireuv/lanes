package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.Action;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;

public class TrapChecker extends CheckerBase {

  private final RandomWrapper randomWrapper;
  private final Tool tool;

  public TrapChecker(ActionFactory actionFactory, RandomWrapper randomWrapper, Tool tool) {
    super(actionFactory);
    this.randomWrapper = randomWrapper;
    this.tool = tool;
  }

  @Override
  public Action check() {
    if (!tool.isTrap()) {
      return actionFactory.createNullAction();
    }

    if (randomWrapper.nextDouble() < Config.getHalfTrapChance()) {
      return actionFactory.createHalfTrapAction();
    } else {
      return actionFactory.createTrapAction();
    }
  }
}
