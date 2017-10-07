package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.ImmutableBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ImmutableTool;

public final class TrapBoardAssigner implements BoardAssigner {

  @Override
  public boolean assignPosition(ImmutableBoard board, Position position) {
    ImmutableTool tool = board.getToolWithoutBoundProtection(position);
    if (!tool.isEmpty()) {
      return false;
    }

    int numOfNonEmpty =
        (boolToInt(!board.getToolWithoutBoundProtection(position.move(-1, 0)).isEmpty())
            + boolToInt(!board.getToolWithoutBoundProtection(position.move(+1, 0)).isEmpty())
            + boolToInt(!board.getToolWithoutBoundProtection(position.move(0, +1)).isEmpty())
            + boolToInt(!board.getToolWithoutBoundProtection(position.move(0, -1)).isEmpty()));
    return numOfNonEmpty >= 2;
  }

  private int boolToInt(boolean bool) {
    return bool ? 1 : 0;
  }
}
