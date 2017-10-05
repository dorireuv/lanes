package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StarBoardAssignerTest {
  private Board board;
  private StarBoardAssigner starBoardAssigner;

  @BeforeEach
  void setUp() {
    board = new SimpleBoard();
    starBoardAssigner = new StarBoardAssigner();
  }

  @Test
  void assignOnEmptyToolAccepted() throws Exception {
    Position position = Position.create(5, 5);
    boolean accepted = starBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  void assignOStarToolRejected() throws Exception {
    Position position = Position.create(5, 5);
    Tool starTool = Tool.newStarTool();
    board.setTool(position, starTool);
    boolean accepted = starBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }

  @Test
  void assignNearThreeOrMoreStarsRejectedLeft() throws Exception {
    Position starPosition = Position.create(5, 5);
    assertAssignNearThreeOrMoreStarsRejected(
        starPosition,
        new Position[] {
          starPosition.move(-1, -1), starPosition.move(0, -1), starPosition.move(+1, -1),
        });
  }

  @Test
  void assignNearThreeOrMoreStarsRejectedRight() throws Exception {
    Position starPosition = Position.create(5, 5);
    assertAssignNearThreeOrMoreStarsRejected(
        starPosition,
        new Position[] {
          starPosition.move(-1, +1), starPosition.move(0, +1), starPosition.move(+1, +1),
        });
  }

  @Test
  void assignNearThreeOrMoreStarsRejectedTop() throws Exception {
    Position starPosition = Position.create(5, 5);
    assertAssignNearThreeOrMoreStarsRejected(
        starPosition,
        new Position[] {
          starPosition.move(-1, -1), starPosition.move(-1, 0), starPosition.move(-1, +1),
        });
  }

  @Test
  void assignNearThreeOrMoreStarsRejectedBottom() throws Exception {
    Position starPosition = Position.create(5, 5);
    assertAssignNearThreeOrMoreStarsRejected(
        starPosition,
        new Position[] {
          starPosition.move(+1, -1), starPosition.move(+1, 0), starPosition.move(+1, +1),
        });
  }

  @Test
  void assignNearThreeOrMoreStarsRejectedAround() throws Exception {
    Position starPosition = Position.create(5, 5);
    assertAssignNearThreeOrMoreStarsRejected(
        starPosition,
        new Position[] {
          starPosition.move(0, -1),
          starPosition.move(0, +1),
          starPosition.move(-1, 0),
          starPosition.move(+1, 0),
        });
  }

  private void assertAssignNearThreeOrMoreStarsRejected(
      Position starPosition, Position[] otherStarsPosition) {
    for (Position otherStarPosition : otherStarsPosition) {
      board.setTool(otherStarPosition, Tool.newStarTool());
    }
    boolean accepted = starBoardAssigner.assignPosition(board, starPosition);
    assertFalse(accepted);
  }
}
