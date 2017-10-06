package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

public enum ToolType {
  EMPTY(false, true),
  COMPANY(false, false),
  STAR(true, false),
  HIT(true, false),
  GOLD_STAR(true, false),
  ;

  private final boolean isStar;
  private final boolean isEmpty;

  ToolType(boolean isStar, boolean isEmpty) {
    this.isStar = isStar;
    this.isEmpty = isEmpty;
  }

  public boolean isStar() {
    return isStar;
  }

  public boolean isEmpty() {
    return isEmpty;
  }
}
