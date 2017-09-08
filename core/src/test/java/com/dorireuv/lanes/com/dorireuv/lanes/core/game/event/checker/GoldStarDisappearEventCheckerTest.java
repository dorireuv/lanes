package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.GoldStarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class GoldStarDisappearEventCheckerTest extends TestBase {

  @Mock
  private ActionFactory actionFactory;
  @Mock
  private RandomWrapper randomWrapper;

  @Test
  public void testCheckDispatchesEventOnGoldStarDisappear() throws Exception {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    board.setTool(position, new GoldStarTool());
    when(randomWrapper.nextPosition(board)).thenReturn(position);

    GoldStarDisappearChecker goldStarDisappearEventChecker =
        new GoldStarDisappearChecker(actionFactory, randomWrapper, board);
    goldStarDisappearEventChecker.check();
    verify(actionFactory, times(1)).createGoldStarDisappearAction(position);
    verifyZeroInteractions(actionFactory);
  }

  @Test
  public void testCheckDoesNothingOnNonGoldStarDisappear() throws Exception {
    Board board = new SimpleBoard();
    final Position position = Position.create(5, 5);
    when(randomWrapper.nextPosition(board))
        .thenAnswer(
            invocationOnMock -> position);

    GoldStarDisappearChecker goldStarDisappearEventChecker =
        new GoldStarDisappearChecker(actionFactory, randomWrapper, board);
    goldStarDisappearEventChecker.check();
    verify(actionFactory, times(1)).createNullAction();
    verifyZeroInteractions(actionFactory);
  }
}
