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
final class GoldStarDisappearEventCheckerTest {

  @Mock private ActionFactory actionFactory;
  @Mock private RandomWrapper randomWrapper;

  @Test
  void checkDispatchesEventOnGoldStarDisappear() throws Exception {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    board.setGoldStar(position);
    when(randomWrapper.nextPosition(board)).thenReturn(position);

    GoldStarDisappearChecker goldStarDisappearEventChecker =
        new GoldStarDisappearChecker(actionFactory, randomWrapper, board);
    goldStarDisappearEventChecker.check();
    verify(actionFactory, times(1)).createGoldStarDisappearAction(position);
    verifyZeroInteractions(actionFactory);
  }

  @Test
  void checkDoesNothingOnNonGoldStarDisappear() throws Exception {
    Board board = new SimpleBoard();
    final Position position = Position.create(5, 5);
    when(randomWrapper.nextPosition(board)).thenAnswer(invocationOnMock -> position);

    GoldStarDisappearChecker goldStarDisappearEventChecker =
        new GoldStarDisappearChecker(actionFactory, randomWrapper, board);
    goldStarDisappearEventChecker.check();
    verify(actionFactory, times(1)).createNullAction();
    verifyZeroInteractions(actionFactory);
  }
}
