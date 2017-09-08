package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class ActionGroup implements Action {

  private final Collection<Action> actions;

  public ActionGroup() {
    this(new LinkedList<>());
  }

  public ActionGroup(Action... actions) {
    this(Arrays.asList(actions));
  }

  private ActionGroup(Collection<Action> actions) {
    this.actions = actions;
  }

  public void addAction(Action action) {
    actions.add(action);
  }

  @Override
  public void doAction() {
    for (Action action : actions) {
      action.doAction();
    }
  }
}
