package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
final class TrapCheckerTest {

  @Mock private ActionFactory actionFactory;
  @Mock private RandomWrapper randomWrapper;
  @Mock private Tool tool;

  @Test
  void checkDispatchesEventOnTrap() throws Exception {
    when(tool.isTrap()).thenReturn(true);
    when(randomWrapper.nextDouble()).thenReturn(1.0);
    TrapChecker trapEventChecker = new TrapChecker(actionFactory, randomWrapper, tool);
    trapEventChecker.check();
    verify(actionFactory, times(1)).createTrapAction();
  }

  @Test
  void checkDispatchesEventOnHalfTrap() throws Exception {
    when(tool.isTrap()).thenReturn(true);
    when(randomWrapper.nextDouble()).thenReturn(0.0);
    TrapChecker trapEventChecker = new TrapChecker(actionFactory, randomWrapper, tool);
    trapEventChecker.check();
    verify(actionFactory, times(1)).createHalfTrapAction();
    verifyZeroInteractions(actionFactory);
  }

  @Test
  void checkDoesNothingOnNonTrap() throws Exception {
    when(tool.isTrap()).thenReturn(false);
    TrapChecker trapEventChecker = new TrapChecker(actionFactory, randomWrapper, tool);
    trapEventChecker.check();
    verify(actionFactory, times(1)).createNullAction();
    verifyZeroInteractions(actionFactory);
  }
}
