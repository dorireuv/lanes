package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;

@SuppressWarnings("UnusedDeclaration")
public class HitEvent implements Event {

  private final Position position;

  public HitEvent(Position position) {
    this.position = position;
  }

  public Position getPosition() {
    return position;
  }
}
