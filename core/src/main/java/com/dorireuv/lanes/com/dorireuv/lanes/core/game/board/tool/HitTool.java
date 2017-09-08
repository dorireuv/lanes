package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.data.HitData;

public class HitTool extends NonCompanyTool {

  private final HitData data;

  public HitTool() {
    super();
    this.data = new HitData();
  }

  public HitTool(Tool tool) {
    super(tool);
    this.data = new HitData();
  }

  public HitData getData() {
    return data;
  }
}
