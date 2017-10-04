package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class SimpleBoardTest {

  private Board board;

  @Before
  public void setUp() {
    board = new SimpleBoard();
  }

  @Test
  public void create() throws Exception {
    assertNotNull(board);
  }

  @Test
  public void getToolWithoutBoundProtectionWhenOutOfBoundsReturnsEmptyTool() {
    assertEquals(
        board.getToolWithoutBoundProtection(Position.create(-1, -1)).getToolType(), ToolType.EMPTY);
  }

  @Test
  public void getToolWithoutBoundProtectionWhenInBoundsReturnsTool() {
    Position position = Position.create(2, 1);
    Tool tool = Tool.newStarTool();
    board.setTool(position, tool);
    assertSame(board.getToolWithoutBoundProtection(position), tool);
  }

  @Test
  public void getToolsAround() throws Exception {
    Position position = Position.create(5, 5);
    Tool leftTool = new Tool();
    Tool rightTool = new Tool();
    Tool topTool = new Tool();
    Tool bottomTool = new Tool();
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
}
