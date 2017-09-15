package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ActionGroupTest {

  @Rule
  public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock
  private Action action1;
  @Mock
  private Action action2;

  @Test
  public void testCheck() throws Exception {
    ActionGroup actionGroup = new ActionGroup(action1, action2);
    actionGroup.doAction();
    verify(action1, times(1)).doAction();
    verify(action2, times(1)).doAction();
  }
}
