package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class TrapCheckerTest extends TestBase {

  @Mock
  private ActionFactory actionFactory;
  @Mock
  private RandomWrapper randomWrapper;
  @Mock
  private Tool tool;

  @Test
  public void testCheckDispatchesEventOnTrap() throws Exception {
    when(tool.isTrap()).thenReturn(true);
    when(randomWrapper.nextDouble()).thenReturn(1.0);
    TrapChecker trapEventChecker = new TrapChecker(actionFactory, randomWrapper, tool);
    trapEventChecker.check();
    verify(actionFactory, times(1)).createTrapAction();
  }

  @Test
  public void testCheckDispatchesEventOnHalfTrap() throws Exception {
    when(tool.isTrap()).thenReturn(true);
    when(randomWrapper.nextDouble()).thenReturn(0.0);
    TrapChecker trapEventChecker = new TrapChecker(actionFactory, randomWrapper, tool);
    trapEventChecker.check();
    verify(actionFactory, times(1)).createHalfTrapAction();
    verifyZeroInteractions(actionFactory);
  }

  @Test
  public void testCheckDoesNothingOnNonTrap() throws Exception {
    when(tool.isTrap()).thenReturn(false);
    TrapChecker trapEventChecker = new TrapChecker(actionFactory, randomWrapper, tool);
    trapEventChecker.check();
    verify(actionFactory, times(1)).createNullAction();
    verifyZeroInteractions(actionFactory);
  }
}
