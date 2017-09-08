package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Position {

  public static Position create(int row, int col) {
    return new AutoValue_Position(row, col);
  }

  public abstract int getRow();

  public abstract int getCol();

  public Position move(int deltaRow, int deltaCol) {
    return create(getRow() + deltaRow, getCol() + deltaCol);
  }
}
