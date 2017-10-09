package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker.Checker;
import javax.inject.Inject;

public class CheckerActionExecutor {

  @Inject
  public CheckerActionExecutor() {}

  public CheckerActionExecutor execute(Checker checker) {
    checker.check().doAction();
    return this;
  }
}
