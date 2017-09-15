package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

public enum ToolType {
  EMPTY(false),
  COMPANY(false),
  STAR(true),
  HIT(true),
  GOLD_STAR(true),;

  private final boolean isStar;

  ToolType(boolean isStar) {
    this.isStar = isStar;
  }

  public boolean isStar() {
    return isStar;
  }
}
