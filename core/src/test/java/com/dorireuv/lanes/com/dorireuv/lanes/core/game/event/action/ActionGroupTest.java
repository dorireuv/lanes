package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ActionGroupTest extends TestBase {

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
