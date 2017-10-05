package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class ActionGroupTest {

  @Mock private Action action1;
  @Mock private Action action2;

  @Test
  void check() throws Exception {
    ActionGroup actionGroup = new ActionGroup(action1, action2);
    actionGroup.doAction();
    verify(action1, times(1)).doAction();
    verify(action2, times(1)).doAction();
  }
}
