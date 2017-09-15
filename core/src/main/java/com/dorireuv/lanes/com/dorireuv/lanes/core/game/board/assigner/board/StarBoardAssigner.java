package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;

public class StarBoardAssigner implements BoardAssigner {

  private boolean doesToolExist(Board board, ToolType toolType, Position position) {
    return board.getToolWithoutBoundProtection(position).getToolType().equals(toolType);
  }

  @Override
  public boolean assignPosition(Board board, Position position) {
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

    if (doesToolExist(board, ToolType.STAR, position.move(-1, +1))
        && doesToolExist(board, ToolType.STAR, position.move(0, +1))
        && doesToolExist(board, ToolType.STAR, position.move(+1, +1))) {
      return false;
    }

    board.setTool(position, Tool.newStarTool());
    return true;
  }
}
