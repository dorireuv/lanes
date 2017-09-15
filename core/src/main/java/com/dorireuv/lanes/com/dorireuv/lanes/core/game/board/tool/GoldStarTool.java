package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.data.GoldStarData;

public class GoldStarTool extends NonCompanyTool {

  private final GoldStarData data;

  public GoldStarTool() {
    super();
    this.data = new GoldStarData();
  }

  @Override
  public GoldStarData getData() {
    return data;
  }
}
