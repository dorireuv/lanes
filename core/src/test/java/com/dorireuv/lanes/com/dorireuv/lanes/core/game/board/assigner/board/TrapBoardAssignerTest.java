package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import org.junit.Before;
import org.junit.Test;

public class TrapBoardAssignerTest {
  private Board board;
  private TrapBoardAssigner trapBoardAssigner;

  @Before
  public void setUp() {
    board = new SimpleBoard();
    trapBoardAssigner = new TrapBoardAssigner();
  }

  @Test
  public void assignRejectedOneStarLeft() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(
        trapPosition,
        new Position[] {
          trapPosition.move(0, -1),
        });
  }

  @Test
  public void assignRejectedOneStarRight() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(
        trapPosition,
        new Position[] {
          trapPosition.move(0, +1),
        });
  }

  @Test
  public void assignRejectedOneStarTop() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(
        trapPosition,
        new Position[] {
          trapPosition.move(-1, 0),
        });
  }

  @Test
  public void assignRejectedOneStarBottom() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(
        trapPosition,
        new Position[] {
          trapPosition.move(+1, 0),
        });
  }

  @Test
  public void assignRejectedNoStars() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(trapPosition, new Position[] {});
  }

  private void assertAssignRejected(Position trapPosition, Position[] otherStarsPosition) {
    for (Position otherStarPosition : otherStarsPosition) {
      board.setTool(otherStarPosition, Tool.newStarTool());
    }
    boolean accepted = trapBoardAssigner.assignPosition(board, trapPosition);
    assertFalse(accepted);
  }

  @Test
  public void assignAllAroundEmptyRejected() throws Exception {
    Position position = Position.create(5, 5);
    boolean accepted = trapBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }

  @Test
  public void assignOnTrapAccepted() throws Exception {
    Position position = Position.create(5, 5);
    board.setTool(position.move(-1, 0), Tool.newStarTool());
    board.setTool(position.move(+1, 0), Tool.newStarTool());
    board.getTool(position).setIsTrap(true);
    boolean accepted = trapBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  public void assignOnNonEmptyToolRejected() throws Exception {
    Position position = Position.create(5, 5);
    Tool nonEmptyTool = Tool.newStarTool();
    board.setTool(position, nonEmptyTool);
    boolean accepted = trapBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }

  @Test
  public void assignAcceptedTwoStarsTopLeft() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(-1, 0), trapPosition.move(0, -1),
        });
  }

  @Test
  public void assignAcceptedTwoStarsTopRight() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(-1, 0), trapPosition.move(0, +1),
        });
  }

  @Test
  public void assignAcceptedTwoStarsBottomLeft() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(+1, 0), trapPosition.move(0, -1),
        });
  }

  @Test
  public void assignAcceptedTwoStarsBottomRight() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(+1, 0), trapPosition.move(0, +1),
        });
  }

  @Test
  public void assignAcceptedThreeStarsLeft() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(-1, 0), trapPosition.move(0, -1), trapPosition.move(+1, 0),
        });
  }

  @Test
  public void assignAcceptedThreeStarsRight() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(-1, 0), trapPosition.move(0, +1), trapPosition.move(+1, 0),
        });
  }

  @Test
  public void assignAcceptedThreeStarsTop() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(0, -1), trapPosition.move(-1, 0), trapPosition.move(0, +1),
        });
  }

  @Test
  public void assignAcceptedThreeStarsBottom() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(0, -1), trapPosition.move(+1, 0), trapPosition.move(0, +1),
        });
  }

  @Test
  public void assignAcceptedFourStars() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(0, -1),
          trapPosition.move(0, +1),
          trapPosition.move(-1, 0),
          trapPosition.move(+1, 0),
        });
  }

  private void assertAssignAccepted(Position trapPosition, Position[] otherStarsPosition) {
    for (Position otherStarPosition : otherStarsPosition) {
      board.setTool(otherStarPosition, Tool.newGoldStarTool());
    }
    boolean accepted = trapBoardAssigner.assignPosition(board, trapPosition);
    assertTrue(accepted);
  }
}
