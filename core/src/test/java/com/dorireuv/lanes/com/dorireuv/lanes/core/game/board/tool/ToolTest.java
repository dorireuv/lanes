package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToolTest {

  @Test
  public void testEquals() throws Exception {
    Tool tool1 = new EmptyTool();
    Tool tool2 = new EmptyTool();
    assertEquals(tool1, tool2);
  }
}
