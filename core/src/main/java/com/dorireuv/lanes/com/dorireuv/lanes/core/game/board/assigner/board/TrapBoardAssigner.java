package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;

public class TrapBoardAssigner implements BoardAssigner {

  private int boolToInt(boolean bool) {
    return bool ? 1 : 0;
  }

  @Override
  public boolean assignPosition(Board board, Position position) {
    Tool tool = board.getToolWithoutBoundProtection(position);
    if (!tool.isEmpty()) {
      return false;
    }

    int numOfStars =
        (this.boolToInt(!board.getToolWithoutBoundProtection(position.move(-1, 0)).isEmpty())
            + this.boolToInt(!board.getToolWithoutBoundProtection(position.move(+1, 0)).isEmpty())
            + this.boolToInt(!board.getToolWithoutBoundProtection(position.move(0, +1)).isEmpty())
            + this.boolToInt(!board.getToolWithoutBoundProtection(position.move(0, -1)).isEmpty()));
    if (numOfStars < 2) {
      return false;
    }

    tool.setIsTrap(true);
    return true;
  }
}
