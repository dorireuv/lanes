package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
final class FreezeCheckerTest {

  @Mock private ActionFactory actionFactory;
  @Mock private Tool tool;

  @Test
  void checkDispatchesEventOnFreeze() throws Exception {
    when(tool.isFreeze()).thenReturn(true);
    FreezeChecker freezeEventChecker = new FreezeChecker(actionFactory, tool);
    freezeEventChecker.check();
    verify(actionFactory, times(1)).createFreezeAction();
    verifyZeroInteractions(actionFactory);
  }

  @Test
  void checkDoesNothingOnNonFreeze() throws Exception {
    when(tool.isFreeze()).thenReturn(false);
    FreezeChecker freezeEventChecker = new FreezeChecker(actionFactory, tool);
    freezeEventChecker.check();
    verify(actionFactory, times(1)).createNullAction();
    verifyZeroInteractions(actionFactory);
  }
}
