package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.GoldStarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.StarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;

public class GoldStarBoardAssigner implements BoardAssigner {

  private boolean doesToolExist(Board board, Class<? extends Tool> toolClass, Position position) {
    return board.getToolWithoutBoundProtection(position).getClass() == toolClass;
  }

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
    if (tool instanceof GoldStarTool) {
      return false;
    }

    if (doesToolExist(board, GoldStarTool.class, position.move(-1, 0))
        || doesToolExist(board, GoldStarTool.class, position.move(+1, 0))
        || doesToolExist(board, GoldStarTool.class, position.move(0, -1))
        || doesToolExist(board, GoldStarTool.class, position.move(0, +1))) {
      return false;
    }

    if (doesToolExist(board, StarTool.class, position.move(0, -1))
        && doesToolExist(board, StarTool.class, position.move(0, +1))
        && doesToolExist(board, StarTool.class, position.move(-1, 0))
        && doesToolExist(board, StarTool.class, position.move(+1, 0))) {
      return false;
    }

    if (doesToolExist(board, StarTool.class, position.move(0, -1))
        && doesToolExist(board, StarTool.class, position.move(0, +1))
        && doesToolExist(board, StarTool.class, position.move(-1, 0))) {
      return false;
    }

    if (doesToolExist(board, StarTool.class, position.move(0, -1))
        && doesToolExist(board, StarTool.class, position.move(0, +1))
        && doesToolExist(board, StarTool.class, position.move(+1, 0))) {
      return false;
    }

    if (doesToolExist(board, StarTool.class, position.move(-1, 0))
        && doesToolExist(board, StarTool.class, position.move(+1, 0))
        && doesToolExist(board, StarTool.class, position.move(0, -1))) {
      return false;
    }

    if (doesToolExist(board, StarTool.class, position.move(-1, 0))
        && doesToolExist(board, StarTool.class, position.move(+1, 0))
        && doesToolExist(board, StarTool.class, position.move(0, +1))) {
      return false;
    }

    board.setTool(position, new GoldStarTool());
    return true;
  }
}
