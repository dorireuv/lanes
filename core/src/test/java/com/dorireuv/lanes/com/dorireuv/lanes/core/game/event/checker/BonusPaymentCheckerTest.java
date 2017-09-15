package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.GoldStarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class BonusPaymentCheckerTest {

  @Rule
  public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock
  private ActionFactory actionFactory;
  @Mock
  private RandomWrapper randomWrapper;

  @Test
  public void testCheckDispatchesEventOnBonusPayment() throws Exception {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    board.setTool(position, new GoldStarTool());
    when(randomWrapper.nextDouble()).thenReturn(0.0);

    BonusPaymentChecker bonusPaymentEventChecker =
        new BonusPaymentChecker(actionFactory, randomWrapper);
    bonusPaymentEventChecker.check();
    verify(actionFactory, times(1)).createBonusPaymentAction();
    verifyZeroInteractions(actionFactory);
  }

  @Test
  public void testCheckDoesNothingOnNonBonusPayment() throws Exception {
    when(randomWrapper.nextDouble()).thenReturn(1.0);
    BonusPaymentChecker bonusPaymentEventChecker =
        new BonusPaymentChecker(actionFactory, randomWrapper);
    bonusPaymentEventChecker.check();
    verify(actionFactory, times(1)).createNullAction();
    verifyZeroInteractions(actionFactory);
  }
}
