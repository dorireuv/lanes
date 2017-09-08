package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.GoldStarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.StarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import org.junit.Before;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GoldStarBoardAssignerTest extends TestBase {
  private Board board;
  private GoldStarBoardAssigner goldStarBoardAssigner;

  @Before
  public void setUp() {
    board = new SimpleBoard();
    goldStarBoardAssigner = new GoldStarBoardAssigner();
  }

  @Test
  public void testAssignOnEmptyToolAccepted() {
    Position position = Position.create(5, 5);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertTrue(accepted);
  }

  @DataProvider(name = "assignOnEmptyToolRejectedPositions")
  public Object[][] assignOnEmptyToolRejectedPositions() {
    return new Object[][]{
        {"First row", Position.create(0, 5)},
        {"Last row", Position.create(board.getRows() - 1, 5)},
        {"First column", Position.create(5, 0)},
        {"Second column", Position.create(5, 1)},
        {"Last column", Position.create(5, board.getCols() - 1)},
        {"Before last column", Position.create(5, board.getCols() - 2)},
    };
  }

  @Test(dataProvider = "assignOnEmptyToolRejectedPositions")
  public void testAssignOnEmptyToolRejected(String description, Position position) {
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertFalse(description + " position should be rejected", accepted);
  }

  @Test
  public void testAssignOnGoldStarToolRejected() {
    Position position = Position.create(5, 5);
    Tool goldStarTool = new GoldStarTool();
    board.setTool(position, goldStarTool);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertFalse(accepted);
  }

  @DataProvider(name = "assignNearAnotherGoldStarPositions")
  public Object[][] assignNearAnotherGoldStarPositions() {
    Position goldStar1Position = Position.create(5, 5);
    return new Object[][]{
        {"left", goldStar1Position.move(0, -1), goldStar1Position},
        {"right", goldStar1Position.move(0, +1), goldStar1Position},
        {"top", goldStar1Position.move(-1, 0), goldStar1Position},
        {"bottom", goldStar1Position.move(+1, 0), goldStar1Position},
    };
  }

  @Test(dataProvider = "assignNearAnotherGoldStarPositions")
  public void testAssignNearAnotherGoldStarRejected(
      String side, Position goldStar1Position, Position goldStar2Position) {
    board.setTool(goldStar1Position, new GoldStarTool());
    boolean accepted = goldStarBoardAssigner.assignPosition(board, goldStar2Position);
    assertFalse(String.format("There is already gold star on %s side", side), accepted);
  }

  @DataProvider(name = "assignNearThreeStarsPositions")
  public Object[][] assignNearThreeStarsPositions() {
    Position goldStarPosition = Position.create(5, 5);
    return new Object[][]{
        {
            "left",
            goldStarPosition,
            new Position[]{
                goldStarPosition.move(0, -1),
                goldStarPosition.move(-1, 0),
                goldStarPosition.move(0, +1),
            },
        },
        {
            "right",
            goldStarPosition,
            new Position[]{
                goldStarPosition.move(0, -1),
                goldStarPosition.move(+1, 0),
                goldStarPosition.move(0, +1),
            },
        },
        {
            "top",
            goldStarPosition,
            new Position[]{
                goldStarPosition.move(-1, 0),
                goldStarPosition.move(0, -1),
                goldStarPosition.move(+1, 0),
            },
        },
        {
            "bottom",
            goldStarPosition,
            new Position[]{
                goldStarPosition.move(-1, 0),
                goldStarPosition.move(0, +1),
                goldStarPosition.move(+1, 0),
            },
        },
        {
            "around",
            goldStarPosition,
            new Position[]{
                goldStarPosition.move(0, -1),
                goldStarPosition.move(0, +1),
                goldStarPosition.move(-1, 0),
                goldStarPosition.move(+1, 0),
            },
        },
    };
  }

  @Test(dataProvider = "assignNearThreeStarsPositions")
  public void testAssignNearThreeOrMoreStarsRejected(
      String side, Position goldStarPosition, Position[] starsPosition) {
    for (Position starPosition : starsPosition) {
      board.setTool(starPosition, new StarTool());
    }
    boolean accepted = goldStarBoardAssigner.assignPosition(board, goldStarPosition);
    assertFalse(String.format("There is already three stars on %s side", side), accepted);
  }
}
