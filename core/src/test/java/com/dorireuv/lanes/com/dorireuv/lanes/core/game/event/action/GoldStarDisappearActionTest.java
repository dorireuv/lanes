package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import static org.junit.Assert.assertEquals;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class GoldStarDisappearActionTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ClientEventSubscriber clientEventSubscriber;

  @Test
  public void doAction() {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    board.setTool(position, Tool.newGoldStarTool());

    GoldStarDisappearAction goldStarDisappearAction =
        new GoldStarDisappearAction(clientEventSubscriber, board, position);
    goldStarDisappearAction.doAction();

    assertEquals(board.getTool(position).getToolType(), ToolType.EMPTY);
  }
}
