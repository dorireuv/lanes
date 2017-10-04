package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import org.junit.Before;
import org.junit.Test;

public class DoublePaymentBoardAssignerTest {
  private Board board;
  private DoublePaymentBoardAssigner doublePaymentBoardAssigner;

  @Before
  public void setUp() {
    board = new SimpleBoard();
    doublePaymentBoardAssigner = new DoublePaymentBoardAssigner();
  }

  @Test
  public void assignOnEmptyToolAccepted() throws Exception {
    Position position = Position.create(0, 0);
    boolean accepted = doublePaymentBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  public void assignOnDoublePaymentAccepted() throws Exception {
    Position position = Position.create(0, 0);
    board.getTool(position).setIsDoublePayment(true);
    boolean accepted = doublePaymentBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  public void assignOnNonEmptyToolRejected() throws Exception {
    Position position = Position.create(0, 0);
    Tool nonEmptyTool = Tool.newStarTool();
    board.setTool(position, nonEmptyTool);
    boolean accepted = doublePaymentBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }
}
