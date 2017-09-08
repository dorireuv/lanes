package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.data;

abstract class DataBase implements Data {
  public boolean equals(Object other) {
    return getClass().equals(other.getClass());
  }

  public int hashCode() {
    return getClass().hashCode();
  }
}
