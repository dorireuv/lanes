package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.EmptyTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.GoldStarTool;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class GoldStarDisappearActionTest extends TestBase {

  @Mock
  private ClientEventSubscriber clientEventSubscriber;

  @Test
  public void testDoAction() {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    board.setTool(position, new GoldStarTool());

    GoldStarDisappearAction goldStarDisappearAction =
        new GoldStarDisappearAction(clientEventSubscriber, board, position);
    goldStarDisappearAction.doAction();

    assertEquals(board.getTool(position).getClass(), EmptyTool.class);
  }
}
