package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class FreezeCheckerTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ActionFactory actionFactory;
  @Mock private Tool tool;

  @Test
  public void testCheckDispatchesEventOnFreeze() throws Exception {
    when(tool.isFreeze()).thenReturn(true);
    FreezeChecker freezeEventChecker = new FreezeChecker(actionFactory, tool);
    freezeEventChecker.check();
    verify(actionFactory, times(1)).createFreezeAction();
    verifyZeroInteractions(actionFactory);
  }

  @Test
  public void testCheckDoesNothingOnNonFreeze() throws Exception {
    when(tool.isFreeze()).thenReturn(false);
    FreezeChecker freezeEventChecker = new FreezeChecker(actionFactory, tool);
    freezeEventChecker.check();
    verify(actionFactory, times(1)).createNullAction();
    verifyZeroInteractions(actionFactory);
  }
}
