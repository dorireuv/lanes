package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;

public class DoublePaymentBoardAssigner implements BoardAssigner {

  @Override
  public boolean assignPosition(Board board, Position position) {
    Tool tool = board.getToolWithoutBoundProtection(position);
    if (!tool.isEmpty()) {
      return false;
    }

    tool.setIsDoublePayment(true);
    return true;
  }
}
