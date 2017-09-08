package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToolTest extends TestBase {

  @Test
  public void testEquals() throws Exception {
    Tool tool1 = new EmptyTool();
    Tool tool2 = new EmptyTool();
    assertEquals(tool1, tool2);
  }
}
