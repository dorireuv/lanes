package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;

public class GoldStarDisappearEvent implements Event {

  private final Position position;

  public GoldStarDisappearEvent(Position position) {
    this.position = position;
  }

  public Position getPosition() {
    return position;
  }
}
