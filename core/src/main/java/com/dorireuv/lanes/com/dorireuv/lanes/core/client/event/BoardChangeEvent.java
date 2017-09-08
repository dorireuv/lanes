package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.data.Data;

@SuppressWarnings("UnusedDeclaration")
public class BoardChangeEvent implements Event {

  private final Position position;
  private final Data data;

  public BoardChangeEvent(Position position, Data data) {
    this.position = position;
    this.data = data;
  }

  public Position getPosition() {
    return position;
  }

  public Data getData() {
    return data;
  }
}
