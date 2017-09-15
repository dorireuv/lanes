package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.EmptyTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.HitTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.StarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.data.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class SimpleBoardTest {

  private Board board;

  @Before
  public void setUp() {
    board = new SimpleBoard();
  }

  @Test
  public void testCreate() throws Exception {
    assertNotNull(board);
  }

  @Test
  public void testGetToolWithoutBoundProtectionWhenOutOfBoundsReturnsEmptyTool() {
    assertEquals(board.getToolWithoutBoundProtection(Position.create(-1, -1)), new EmptyTool());
  }

  @Test
  public void testGetToolWithoutBoundProtectionWhenInBoundsReturnsTool() {
    Position position = Position.create(2, 1);
    Tool tool = new StarTool();
    board.setTool(position, tool);
    assertSame(board.getToolWithoutBoundProtection(position), tool);
  }

  @Test
  public void testGetToolsAround() throws Exception {
    Position position = Position.create(5, 5);
    Tool leftTool = new EmptyTool();
    Tool rightTool = new EmptyTool();
    Tool topTool = new EmptyTool();
    Tool bottomTool = new EmptyTool();
    Position leftPosition = position.move(0, -1);
    Position rightPosition = position.move(0, +1);
    Position topPosition = position.move(-1, 0);
    Position bottomPosition = position.move(+1, 0);

    board.setTool(leftPosition, leftTool);
    board.setTool(rightPosition, rightTool);
    board.setTool(topPosition, topTool);
    board.setTool(bottomPosition, bottomTool);
    Map<Position, Tool> toolsAround = board.getToolsAround(position);

    assertSame(toolsAround.get(leftPosition), leftTool);
    assertSame(toolsAround.get(rightPosition), rightTool);
    assertSame(toolsAround.get(topPosition), topTool);
    assertSame(toolsAround.get(bottomPosition), bottomTool);
  }

  @Test
  public void testGetBoardData() throws Exception {
    board.setTool(Position.create(3, 5), new StarTool());
    board.setTool(Position.create(3, 7), new HitTool());
    Data[][] boardData = board.getBoardData();
    for (int row = 0; row < boardData.length; row++) {
      for (int col = 0; col < boardData[row].length; col++) {
        assertEquals(boardData[row][col], board.getTool(row, col).getData());
      }
    }
  }
}
