package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.Action;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;

public class FreezeChecker extends CheckerBase {

  private final Tool tool;

  public FreezeChecker(ActionFactory actionFactory, Tool tool) {
    super(actionFactory);
    this.tool = tool;
  }

  @Override
  public Action check() {
    if (!tool.isFreeze()) {
      return actionFactory.createNullAction();
    }

    return actionFactory.createFreezeAction();
  }
}
