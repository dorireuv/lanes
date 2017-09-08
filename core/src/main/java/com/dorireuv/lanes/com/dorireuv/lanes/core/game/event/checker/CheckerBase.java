package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;

abstract class CheckerBase implements Checker {

  final ActionFactory actionFactory;

  CheckerBase(ActionFactory actionFactory) {
    this.actionFactory = actionFactory;
  }
}
