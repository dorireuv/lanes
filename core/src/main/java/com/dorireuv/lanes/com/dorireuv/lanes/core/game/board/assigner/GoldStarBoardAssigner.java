package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.ImmutableBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ImmutableTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;

public final class GoldStarBoardAssigner implements BoardAssigner {

  @Override
  public boolean assignPosition(ImmutableBoard board, Position position) {
    int row = position.getRow();
    if (row <= 0 || row >= board.getRows() - 1) {
      return false;
    }

    int col = position.getCol();
    if (col <= 1 || col >= board.getCols() - 2) {
      return false;
    }

    ImmutableTool tool = board.getToolWithoutBoundProtection(position);
    if (tool.getToolType().equals(ToolType.GOLD_STAR)) {
      return false;
    }

    if (doesToolExist(board, ToolType.GOLD_STAR, position.move(-1, 0))
        || doesToolExist(board, ToolType.GOLD_STAR, position.move(+1, 0))
        || doesToolExist(board, ToolType.GOLD_STAR, position.move(0, -1))
        || doesToolExist(board, ToolType.GOLD_STAR, position.move(0, +1))) {
      return false;
    }

    if (doesToolExist(board, ToolType.STAR, position.move(0, -1))
        && doesToolExist(board, ToolType.STAR, position.move(0, +1))
        && doesToolExist(board, ToolType.STAR, position.move(-1, 0))
        && doesToolExist(board, ToolType.STAR, position.move(+1, 0))) {
      return false;
    }

    if (doesToolExist(board, ToolType.STAR, position.move(0, -1))
        && doesToolExist(board, ToolType.STAR, position.move(0, +1))
        && doesToolExist(board, ToolType.STAR, position.move(-1, 0))) {
      return false;
    }

    if (doesToolExist(board, ToolType.STAR, position.move(0, -1))
        && doesToolExist(board, ToolType.STAR, position.move(0, +1))
        && doesToolExist(board, ToolType.STAR, position.move(+1, 0))) {
      return false;
    }

    if (doesToolExist(board, ToolType.STAR, position.move(-1, 0))
        && doesToolExist(board, ToolType.STAR, position.move(+1, 0))
        && doesToolExist(board, ToolType.STAR, position.move(0, -1))) {
      return false;
    }

    return !doesToolExist(board, ToolType.STAR, position.move(-1, 0))
        || !doesToolExist(board, ToolType.STAR, position.move(+1, 0))
        || !doesToolExist(board, ToolType.STAR, position.move(0, +1));
  }

  private boolean doesToolExist(ImmutableBoard board, ToolType toolType, Position position) {
    return board.getToolWithoutBoundProtection(position).getToolType().equals(toolType);
  }
}
