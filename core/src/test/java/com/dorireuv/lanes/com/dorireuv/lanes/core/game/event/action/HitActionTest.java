package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import static org.junit.Assert.assertEquals;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class HitActionTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ClientEventSubscriber clientEventSubscriber;

  @Test
  public void testDoAction() throws Exception {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    HitAction hitAction = new HitAction(clientEventSubscriber, board, position);
    hitAction.doAction();
    assertEquals(board.getTool(position).getToolType(), ToolType.HIT);
  }
}
