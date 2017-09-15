package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.GoldStarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.NonEmptyTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.StarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class TrapBoardAssignerTest {
  private Board board;
  private TrapBoardAssigner trapBoardAssigner;

  @Before
  public void setUp() {
    board = new SimpleBoard();
    trapBoardAssigner = new TrapBoardAssigner();
  }

  @Test
  public void testAssignRejectedOneStarLeft() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(
        trapPosition,
        new Position[]{
            trapPosition.move(0, -1),
        });
  }

  @Test
  public void testAssignRejectedOneStarRight() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(
        trapPosition,
        new Position[]{
            trapPosition.move(0, +1),
        });
  }

  @Test
  public void testAssignRejectedOneStarTop() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(
        trapPosition,
        new Position[]{
            trapPosition.move(-1, 0),
        });
  }

  @Test
  public void testAssignRejectedOneStarBottom() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(
        trapPosition,
        new Position[]{
            trapPosition.move(+1, 0),
        });
  }

  @Test
  public void testAssignRejectedNoStars() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignRejected(trapPosition, new Position[]{});
  }

  private void assertAssignRejected(Position trapPosition, Position[] otherStarsPosition) {
    for (Position otherStarPosition : otherStarsPosition) {
      board.setTool(otherStarPosition, new StarTool());
    }
    boolean accepted = trapBoardAssigner.assignPosition(board, trapPosition);
    assertFalse(accepted);
  }

  @Test
  public void testAssignAllAroundEmptyRejected() throws Exception {
    Position position = Position.create(5, 5);
    boolean accepted = trapBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }

  @Test
  public void testAssignOnTrapAccepted() throws Exception {
    Position position = Position.create(5, 5);
    board.setTool(position.move(-1, 0), new StarTool());
    board.setTool(position.move(+1, 0), new StarTool());
    board.getTool(position).setIsTrap(true);
    boolean accepted = trapBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @Test
  public void testAssignOnNonEmptyToolRejected() throws Exception {
    Position position = Position.create(5, 5);
    Tool nonEmptyTool = mock(NonEmptyTool.class);
    board.setTool(position, nonEmptyTool);
    boolean accepted = trapBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }

  @Test
  public void testAssignAcceptedTwoStarsTopLeft() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[]{
            trapPosition.move(-1, 0), trapPosition.move(0, -1),
        });
  }

  @Test
  public void testAssignAcceptedTwoStarsTopRight() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[]{
            trapPosition.move(-1, 0), trapPosition.move(0, +1),
        });
  }

  @Test
  public void testAssignAcceptedTwoStarsBottomLeft() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[]{
            trapPosition.move(+1, 0), trapPosition.move(0, -1),
        });
  }

  @Test
  public void testAssignAcceptedTwoStarsBottomRight() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[]{
            trapPosition.move(+1, 0), trapPosition.move(0, +1),
        });
  }

  @Test
  public void testAssignAcceptedThreeStarsLeft() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[]{
            trapPosition.move(-1, 0), trapPosition.move(0, -1), trapPosition.move(+1, 0),
        });
  }

  @Test
  public void testAssignAcceptedThreeStarsRight() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[]{
            trapPosition.move(-1, 0), trapPosition.move(0, +1), trapPosition.move(+1, 0),
        });
  }

  @Test
  public void testAssignAcceptedThreeStarsTop() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[]{
            trapPosition.move(0, -1), trapPosition.move(-1, 0), trapPosition.move(0, +1),
        });
  }

  @Test
  public void testAssignAcceptedThreeStarsBottom() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[]{
            trapPosition.move(0, -1), trapPosition.move(+1, 0), trapPosition.move(0, +1),
        });
  }

  @Test
  public void testAssignAcceptedFourStars() throws Exception {
    Position trapPosition = Position.create(5, 5);
    assertAssignAccepted(
        trapPosition,
        new Position[]{
            trapPosition.move(0, -1),
            trapPosition.move(0, +1),
            trapPosition.move(-1, 0),
            trapPosition.move(+1, 0),
        });
  }

  private void assertAssignAccepted(Position trapPosition, Position[] otherStarsPosition) {
    for (Position otherStarPosition : otherStarsPosition) {
      board.setTool(otherStarPosition, new GoldStarTool());
    }
    boolean accepted = trapBoardAssigner.assignPosition(board, trapPosition);
    assertTrue(accepted);
  }
}
