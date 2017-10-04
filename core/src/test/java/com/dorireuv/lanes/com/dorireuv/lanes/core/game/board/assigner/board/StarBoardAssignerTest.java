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
  public void assignOnEmptyToolAccepted() throws Exception {
    Position position = Position.create(5, 5);
    boolean accepted = starBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  public void assignOStarToolRejected() throws Exception {
    Position position = Position.create(5, 5);
    Tool starTool = Tool.newStarTool();
    board.setTool(position, starTool);
    boolean accepted = starBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }

  @Test
  public void assignNearThreeOrMoreStarsRejectedLeft() throws Exception {
    Position starPosition = Position.create(5, 5);
    assertAssignNearThreeOrMoreStarsRejected(
        starPosition,
        new Position[] {
          starPosition.move(-1, -1), starPosition.move(0, -1), starPosition.move(+1, -1),
        });
  }

  @Test
  public void assignNearThreeOrMoreStarsRejectedRight() throws Exception {
    Position starPosition = Position.create(5, 5);
    assertAssignNearThreeOrMoreStarsRejected(
        starPosition,
        new Position[] {
          starPosition.move(-1, +1), starPosition.move(0, +1), starPosition.move(+1, +1),
        });
  }

  @Test
  public void assignNearThreeOrMoreStarsRejectedTop() throws Exception {
    Position starPosition = Position.create(5, 5);
    assertAssignNearThreeOrMoreStarsRejected(
        starPosition,
        new Position[] {
          starPosition.move(-1, -1), starPosition.move(-1, 0), starPosition.move(-1, +1),
        });
  }

  @Test
  public void assignNearThreeOrMoreStarsRejectedBottom() throws Exception {
    Position starPosition = Position.create(5, 5);
    assertAssignNearThreeOrMoreStarsRejected(
        starPosition,
        new Position[] {
          starPosition.move(+1, -1), starPosition.move(+1, 0), starPosition.move(+1, +1),
        });
  }

  @Test
  public void assignNearThreeOrMoreStarsRejectedAround() throws Exception {
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
