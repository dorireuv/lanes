package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class BonusPaymentCheckerTest {

  @Mock private ActionFactory actionFactory;
  @Mock private RandomWrapper randomWrapper;

  @Test
  void checkDispatchesEventOnBonusPayment() throws Exception {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    board.setGoldStar(position);
    when(randomWrapper.nextDouble()).thenReturn(0.0);

    BonusPaymentChecker bonusPaymentEventChecker =
        new BonusPaymentChecker(actionFactory, randomWrapper);
    bonusPaymentEventChecker.check();
    verify(actionFactory, times(1)).createBonusPaymentAction();
    verifyZeroInteractions(actionFactory);
  }

  @Test
  void checkDoesNothingOnNonBonusPayment() throws Exception {
    when(randomWrapper.nextDouble()).thenReturn(1.0);
    BonusPaymentChecker bonusPaymentEventChecker =
        new BonusPaymentChecker(actionFactory, randomWrapper);
    bonusPaymentEventChecker.check();
    verify(actionFactory, times(1)).createNullAction();
    verifyZeroInteractions(actionFactory);
  }
}
