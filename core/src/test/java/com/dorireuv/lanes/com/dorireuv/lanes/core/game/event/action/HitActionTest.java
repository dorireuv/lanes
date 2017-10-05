package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import static org.junit.Assert.assertEquals;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class HitActionTest {

  @Mock private ClientEventSubscriber clientEventSubscriber;

  @Test
  void doAction() throws Exception {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    HitAction hitAction = new HitAction(clientEventSubscriber, board, position);
    hitAction.doAction();
    assertEquals(board.getTool(position).getToolType(), ToolType.HIT);
  }
}
