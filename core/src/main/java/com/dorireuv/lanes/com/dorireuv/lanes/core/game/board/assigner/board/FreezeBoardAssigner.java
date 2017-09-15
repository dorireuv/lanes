package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;

public class FreezeBoardAssigner implements BoardAssigner {

  @Override
  public boolean assignPosition(Board board, Position position) {
    Tool tool = board.getToolWithoutBoundProtection(position);
    if (!tool.getToolType().equals(ToolType.EMPTY)) {
      return false;
    }

    tool.setIsFreeze(true);
    return true;
  }
}
