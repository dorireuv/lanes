package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.data.StarData;

public class StarTool extends NonCompanyTool {

  private final StarData data;

  public StarTool() {
    super();
    this.data = new StarData();
  }

  @Override
  public StarData getData() {
    return data;
  }
}
