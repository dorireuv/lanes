package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.ImmutableBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;

public class StarBoardAssigner implements BoardAssigner {

  @Override
  public boolean assignPosition(ImmutableBoard board, Position position) {
    if (doesToolExist(board, ToolType.STAR, position)) {
      return false;
    }

    if (doesToolExist(board, ToolType.STAR, position.move(-1, 0))
        && doesToolExist(board, ToolType.STAR, position.move(+1, 0))
        && doesToolExist(board, ToolType.STAR, position.move(0, +1))
        && doesToolExist(board, ToolType.STAR, position.move(0, -1))) {
      return false;
    }

    if (doesToolExist(board, ToolType.STAR, position.move(-1, -1))
        && doesToolExist(board, ToolType.STAR, position.move(-1, 0))
        && doesToolExist(board, ToolType.STAR, position.move(-1, +1))) {
      return false;
    }

    if (doesToolExist(board, ToolType.STAR, position.move(+1, -1))
        && doesToolExist(board, ToolType.STAR, position.move(+1, 0))
        && doesToolExist(board, ToolType.STAR, position.move(+1, +1))) {
      return false;
    }

    if (doesToolExist(board, ToolType.STAR, position.move(-1, -1))
        && doesToolExist(board, ToolType.STAR, position.move(0, -1))
        && doesToolExist(board, ToolType.STAR, position.move(+1, -1))) {
      return false;
    }

    return !doesToolExist(board, ToolType.STAR, position.move(-1, +1))
        || !doesToolExist(board, ToolType.STAR, position.move(0, +1))
        || !doesToolExist(board, ToolType.STAR, position.move(+1, +1));
  }

  private boolean doesToolExist(ImmutableBoard board, ToolType toolType, Position position) {
    return board.getToolWithoutBoundProtection(position).getToolType().equals(toolType);
  }
}
