package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import org.junit.Before;
import org.junit.Test;

public class FreezeBoardAssignerTest {
  private Board board;
  private FreezeBoardAssigner freezeBoardAssigner;

  @Before
  public void setUp() {
    board = new SimpleBoard();
    freezeBoardAssigner = new FreezeBoardAssigner();
  }

  @Test
  public void testAssignOnEmptyToolAccepted() throws Exception {
    Position position = Position.create(5, 5);
    boolean accepted = freezeBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  public void testAssignOnFreezeAccepted() throws Exception {
    Position position = Position.create(5, 5);
    board.getTool(position).setIsFreeze(true);
    boolean accepted = freezeBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  public void testAssignOnNonEmptyToolRejected() throws Exception {
    Position position = Position.create(5, 5);
    Tool nonEmptyTool = Tool.newStarTool();
    board.setTool(position, nonEmptyTool);
    boolean accepted = freezeBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }
}
