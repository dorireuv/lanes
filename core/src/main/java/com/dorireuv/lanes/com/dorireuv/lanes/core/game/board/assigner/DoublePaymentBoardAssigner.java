package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.ImmutableBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;

public final class DoublePaymentBoardAssigner implements BoardAssigner {

  @Override
  public boolean assignPosition(ImmutableBoard board, Position position) {
    return board.getToolWithoutBoundProtection(position).isEmpty();
  }
}
