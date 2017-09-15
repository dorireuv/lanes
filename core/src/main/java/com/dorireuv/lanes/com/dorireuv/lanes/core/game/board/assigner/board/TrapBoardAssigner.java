package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;

public class TrapBoardAssigner implements BoardAssigner {

  private int boolToInt(boolean bool) {
    return bool ? 1 : 0;
  }

  @Override
  public boolean assignPosition(Board board, Position position) {
    Tool tool = board.getToolWithoutBoundProtection(position);
    if (!tool.getToolType().equals(ToolType.EMPTY)) {
      return false;
    }

    int numOfStars =
        (this.boolToInt(
                !board
                    .getToolWithoutBoundProtection(position.move(-1, 0))
                    .getToolType()
                    .equals(ToolType.EMPTY))
            + this.boolToInt(
                !board
                    .getToolWithoutBoundProtection(position.move(+1, 0))
                    .getToolType()
                    .equals(ToolType.EMPTY))
            + this.boolToInt(
                !board
                    .getToolWithoutBoundProtection(position.move(0, +1))
                    .getToolType()
                    .equals(ToolType.EMPTY))
            + this.boolToInt(
                !board
                    .getToolWithoutBoundProtection(position.move(0, -1))
                    .getToolType()
                    .equals(ToolType.EMPTY)));
    if (numOfStars < 2) {
      return false;
    }

    tool.setIsTrap(true);
    return true;
  }
}
