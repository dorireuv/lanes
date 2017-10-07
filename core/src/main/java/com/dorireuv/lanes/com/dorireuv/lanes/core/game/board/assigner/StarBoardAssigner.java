package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.ImmutableBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;

public final class StarBoardAssigner implements BoardAssigner {

  @Override
  public boolean assignPosition(ImmutableBoard board, Position position) {
    if (isStarTool(board, position)) {
      return false;
    }

    if (isStarTool(board, position.move(-1, 0))
        && isStarTool(board, position.move(+1, 0))
        && isStarTool(board, position.move(0, +1))
        && isStarTool(board, position.move(0, -1))) {
      return false;
    }

    if (isStarTool(board, position.move(-1, -1))
        && isStarTool(board, position.move(-1, 0))
        && isStarTool(board, position.move(-1, +1))) {
      return false;
    }

    if (isStarTool(board, position.move(+1, -1))
        && isStarTool(board, position.move(+1, 0))
        && isStarTool(board, position.move(+1, +1))) {
      return false;
    }

    if (isStarTool(board, position.move(-1, -1))
        && isStarTool(board, position.move(0, -1))
        && isStarTool(board, position.move(+1, -1))) {
      return false;
    }

    return !isStarTool(board, position.move(-1, +1))
        || !isStarTool(board, position.move(0, +1))
        || !isStarTool(board, position.move(+1, +1));
  }

  private static boolean isStarTool(ImmutableBoard board, Position position) {
    return board.getToolWithoutBoundProtection(position).getToolType().equals(ToolType.STAR);
  }
}
