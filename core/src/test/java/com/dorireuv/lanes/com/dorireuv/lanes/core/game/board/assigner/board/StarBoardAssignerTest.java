package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import org.junit.Before;
import org.junit.Test;

public class StarBoardAssignerTest {
  private Board board;
  private StarBoardAssigner starBoardAssigner;

  @Before
  public void setUp() {
    board = new SimpleBoard();
    starBoardAssigner = new StarBoardAssigner();
  }

  @Test
  public void testAssignOnEmptyToolAccepted() throws Exception {
    Position position = Position.create(5, 5);
    boolean accepted = starBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  public void testAssignOStarToolRejected() throws Exception {
    Position position = Position.create(5, 5);
    Tool starTool = Tool.newStarTool();
    board.setTool(position, starTool);
    boolean accepted = starBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }

  @Test
  public void testAssignNearThreeOrMoreStarsRejectedLeft() throws Exception {
    Position starPosition = Position.create(5, 5);
    assertAssignNearThreeOrMoreStarsRejected(
        starPosition,
        new Position[] {
          starPosition.move(-1, -1), starPosition.move(0, -1), starPosition.move(+1, -1),
        });
  }

  @Test
  public void testAssignNearThreeOrMoreStarsRejectedRight() throws Exception {
    Position starPosition = Position.create(5, 5);
    assertAssignNearThreeOrMoreStarsRejected(
        starPosition,
        new Position[] {
          starPosition.move(-1, +1), starPosition.move(0, +1), starPosition.move(+1, +1),
        });
  }

  @Test
  public void testAssignNearThreeOrMoreStarsRejectedTop() throws Exception {
    Position starPosition = Position.create(5, 5);
    assertAssignNearThreeOrMoreStarsRejected(
        starPosition,
        new Position[] {
          starPosition.move(-1, -1), starPosition.move(-1, 0), starPosition.move(-1, +1),
        });
  }

  @Test
  public void testAssignNearThreeOrMoreStarsRejectedBottom() throws Exception {
    Position starPosition = Position.create(5, 5);
    assertAssignNearThreeOrMoreStarsRejected(
        starPosition,
        new Position[] {
          starPosition.move(+1, -1), starPosition.move(+1, 0), starPosition.move(+1, +1),
        });
  }

  @Test
  public void testAssignNearThreeOrMoreStarsRejectedAround() throws Exception {
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
