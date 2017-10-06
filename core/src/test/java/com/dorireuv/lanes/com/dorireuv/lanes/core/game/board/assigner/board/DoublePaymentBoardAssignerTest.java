package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoublePaymentBoardAssignerTest {
  private Board board;
  private DoublePaymentBoardAssigner doublePaymentBoardAssigner;

  @BeforeEach
  void setUp() {
    board = new SimpleBoard();
    doublePaymentBoardAssigner = new DoublePaymentBoardAssigner();
  }

  @Test
  void assignOnEmptyToolAccepted() throws Exception {
    Position position = Position.create(0, 0);
    boolean accepted = doublePaymentBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  void assignOnDoublePaymentAccepted() throws Exception {
    Position position = Position.create(0, 0);
    board.getTool(position).setIsDoublePayment(true);
    boolean accepted = doublePaymentBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  void assignOnNonEmptyToolRejected() throws Exception {
    Position position = Position.create(0, 0);
    board.setStar(position);
    boolean accepted = doublePaymentBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }
}
