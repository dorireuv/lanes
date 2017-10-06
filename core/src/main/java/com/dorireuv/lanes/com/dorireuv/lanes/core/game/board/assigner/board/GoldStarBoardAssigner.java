package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;

public class GoldStarBoardAssigner implements BoardAssigner {

  private boolean doesToolExist(Board board, ToolType toolType, Position position) {
    return board.getToolWithoutBoundProtection(position).getToolType().equals(toolType);
  }

  @Override
  public boolean assignPosition(Board board, Position position) {
    int row = position.getRow();
    if (row <= 0 || row >= board.getRows() - 1) {
      return false;
    }

    int col = position.getCol();
    if (col <= 1 || col >= board.getCols() - 2) {
      return false;
    }

    Tool tool = board.getToolWithoutBoundProtection(position);
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

    if (doesToolExist(board, ToolType.STAR, position.move(-1, 0))
        && doesToolExist(board, ToolType.STAR, position.move(+1, 0))
        && doesToolExist(board, ToolType.STAR, position.move(0, +1))) {
      return false;
    }

    board.setGoldStar(position);
    return true;
  }
}
