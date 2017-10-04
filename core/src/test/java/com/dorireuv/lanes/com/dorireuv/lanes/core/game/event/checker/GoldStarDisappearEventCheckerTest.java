package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class GoldStarDisappearEventCheckerTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ActionFactory actionFactory;
  @Mock private RandomWrapper randomWrapper;

  @Test
  public void checkDispatchesEventOnGoldStarDisappear() throws Exception {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    board.setTool(position, Tool.newGoldStarTool());
    when(randomWrapper.nextPosition(board)).thenReturn(position);

    GoldStarDisappearChecker goldStarDisappearEventChecker =
        new GoldStarDisappearChecker(actionFactory, randomWrapper, board);
    goldStarDisappearEventChecker.check();
    verify(actionFactory, times(1)).createGoldStarDisappearAction(position);
    verifyZeroInteractions(actionFactory);
  }

  @Test
  public void checkDoesNothingOnNonGoldStarDisappear() throws Exception {
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
