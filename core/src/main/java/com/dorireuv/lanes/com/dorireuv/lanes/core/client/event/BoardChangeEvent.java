package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;

@SuppressWarnings("UnusedDeclaration")
public class BoardChangeEvent implements Event {

  private final Position position;
  private final Tool tool;

  public BoardChangeEvent(Position position, Tool tool) {
    this.position = position;
    this.tool = tool;
  }

  public Position getPosition() {
    return position;
  }

  public Tool getTool() {
    return tool;
  }
}
