package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.ImmutableBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;

public class DoublePaymentBoardAssigner implements BoardAssigner {

  @Override
  public boolean assignPosition(ImmutableBoard board, Position position) {
    Tool tool = board.getToolWithoutBoundProtection(position);
    return tool.isEmpty();
  }
}
