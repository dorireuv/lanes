package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.data.EmptyData;

public class EmptyTool extends ToolBase {

  private final EmptyData data;

  public EmptyTool() {
    super();
    this.data = new EmptyData();
  }

  public EmptyTool(Tool tool) {
    super(tool);
    this.data = new EmptyData();
  }

  @Override
  public EmptyData getData() {
    return data;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }
}
