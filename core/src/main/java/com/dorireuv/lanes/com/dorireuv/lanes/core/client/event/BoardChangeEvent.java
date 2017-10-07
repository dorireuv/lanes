package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ImmutableTool;

public class BoardChangeEvent implements Event {

  private final Position position;
  private final ImmutableTool tool;

  public BoardChangeEvent(Position position, ImmutableTool tool) {
    this.position = position;
    this.tool = tool;
  }

  public Position getPosition() {
    return position;
  }

  public ImmutableTool getTool() {
    return tool;
  }
}
