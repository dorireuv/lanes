package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ToolTest {

  @Test
  public void testEquals() throws Exception {
    Tool tool1 = new EmptyTool();
    Tool tool2 = new EmptyTool();
    assertEquals(tool1, tool2);
  }
}
