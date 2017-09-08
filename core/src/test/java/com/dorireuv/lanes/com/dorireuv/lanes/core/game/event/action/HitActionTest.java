package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.HitTool;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class HitActionTest extends TestBase {

  @Mock
  private ClientEventSubscriber clientEventSubscriber;

  @Test
  public void testDoAction() throws Exception {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    HitAction hitAction = new HitAction(clientEventSubscriber, board, position);
    hitAction.doAction();
    assertEquals(board.getTool(position).getClass(), HitTool.class);
  }
}
