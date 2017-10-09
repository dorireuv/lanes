package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class TrapBoardAssignerTest {
  private Board board;
  private TrapBoardAssigner trapBoardAssigner;

  @BeforeEach
  void setUp() {
    board = new SimpleBoard();
    trapBoardAssigner = new TrapBoardAssigner();
  }

  @Test
  void assignRejectedOneStarLeft() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(
        trapPosition,
        new Position[] {
          trapPosition.move(0, -1),
        });
  }

  @Test
  void assignRejectedOneStarRight() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(
        trapPosition,
        new Position[] {
          trapPosition.move(0, +1),
        });
  }

  @Test
  void assignRejectedOneStarTop() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(
        trapPosition,
        new Position[] {
          trapPosition.move(-1, 0),
        });
  }

  @Test
  void assignRejectedOneStarBottom() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(
        trapPosition,
        new Position[] {
          trapPosition.move(+1, 0),
        });
  }

  @Test
  void assignRejectedNoStars() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(trapPosition, new Position[] {});
  }

  private void assertAssignRejected(Position trapPosition, Position[] otherStarsPosition) {
    for (Position otherStarPosition : otherStarsPosition) {
      board.setStar(otherStarPosition);
    }
    boolean accepted = trapBoardAssigner.assignPosition(board, trapPosition);
    assertFalse(accepted);
  }

  @Test
  void assignAllAroundEmptyRejected() throws Exception {
    Position position = Position.create(5, 5);
    boolean accepted = trapBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }

  @Test
  void assignOnTrapAccepted() throws Exception {
    Position position = Position.create(5, 5);
    board.setStar(position.move(-1, 0));
    board.setStar(position.move(+1, 0));
    board.getTool(position).setIsTrap(true);
    boolean accepted = trapBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  void assignOnNonEmptyToolRejected() throws Exception {
    Position position = Position.create(5, 5);
    board.setStar(position);
    boolean accepted = trapBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }

  @Test
  void assignAcceptedTwoStarsTopLeft() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(-1, 0), trapPosition.move(0, -1),
        });
  }

  @Test
  void assignAcceptedTwoStarsTopRight() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(-1, 0), trapPosition.move(0, +1),
        });
  }

  @Test
  void assignAcceptedTwoStarsBottomLeft() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(+1, 0), trapPosition.move(0, -1),
        });
  }

  @Test
  void assignAcceptedTwoStarsBottomRight() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(+1, 0), trapPosition.move(0, +1),
        });
  }

  @Test
  void assignAcceptedThreeStarsLeft() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(-1, 0), trapPosition.move(0, -1), trapPosition.move(+1, 0),
        });
  }

  @Test
  void assignAcceptedThreeStarsRight() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(-1, 0), trapPosition.move(0, +1), trapPosition.move(+1, 0),
        });
  }

  @Test
  void assignAcceptedThreeStarsTop() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(0, -1), trapPosition.move(-1, 0), trapPosition.move(0, +1),
        });
  }

  @Test
  void assignAcceptedThreeStarsBottom() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[] {
          trapPosition.move(0, -1), trapPosition.move(+1, 0), trapPosition.move(0, +1),
        });
  }

  @Test
  void assignAcceptedFourStars() throws Exception {
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
      board.setGoldStar(otherStarPosition);
    }
    boolean accepted = trapBoardAssigner.assignPosition(board, trapPosition);
    assertTrue(accepted);
  }
}
