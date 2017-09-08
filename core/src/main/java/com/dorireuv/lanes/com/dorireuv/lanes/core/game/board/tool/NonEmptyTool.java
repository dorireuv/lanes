package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

public abstract class NonEmptyTool extends ToolBase {
  NonEmptyTool() {
    super();
  }

  NonEmptyTool(Tool tool) {
    super(tool);
  }

  @Override
  public boolean isEmpty() {
    return false;
  }
}
