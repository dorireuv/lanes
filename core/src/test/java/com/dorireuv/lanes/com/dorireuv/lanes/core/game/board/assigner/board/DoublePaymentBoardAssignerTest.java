package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.NonEmptyTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class DoublePaymentBoardAssignerTest extends TestBase {
  private Board board;
  private DoublePaymentBoardAssigner doublePaymentBoardAssigner;

  @Before
  public void setUp() {
    board = new SimpleBoard();
    doublePaymentBoardAssigner = new DoublePaymentBoardAssigner();
  }

  @Test
  public void testAssignOnEmptyToolAccepted() throws Exception {
    Position position = Position.create(0, 0);
    boolean accepted = doublePaymentBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  public void testAssignOnDoublePaymentAccepted() throws Exception {
    Position position = Position.create(0, 0);
    board.getTool(position).setIsDoublePayment(true);
    boolean accepted = doublePaymentBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  public void testAssignOnNonEmptyToolRejected() throws Exception {
    Position position = Position.create(0, 0);
    Tool nonEmptyTool = mock(NonEmptyTool.class);
    board.setTool(position, nonEmptyTool);
    boolean accepted = doublePaymentBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }
}
