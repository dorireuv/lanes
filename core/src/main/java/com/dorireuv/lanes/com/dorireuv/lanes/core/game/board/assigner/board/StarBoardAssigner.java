package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.StarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;

public class StarBoardAssigner implements BoardAssigner {

  private boolean doesToolExist(Board board, Class<? extends Tool> toolClass, Position position) {
    return board.getToolWithoutBoundProtection(position).getClass() == toolClass;
  }

  public boolean assignPosition(Board board, Position position) {
    if (doesToolExist(board, StarTool.class, position)) {
      return false;
    }

    if (doesToolExist(board, StarTool.class, position.move(-1, 0))
        && doesToolExist(board, StarTool.class, position.move(+1, 0))
        && doesToolExist(board, StarTool.class, position.move(0, +1))
        && doesToolExist(board, StarTool.class, position.move(0, -1))) {
      return false;
    }

    if (doesToolExist(board, StarTool.class, position.move(-1, -1))
        && doesToolExist(board, StarTool.class, position.move(-1, 0))
        && doesToolExist(board, StarTool.class, position.move(-1, +1))) {
      return false;
    }

    if (doesToolExist(board, StarTool.class, position.move(+1, -1))
        && doesToolExist(board, StarTool.class, position.move(+1, 0))
        && doesToolExist(board, StarTool.class, position.move(+1, +1))) {
      return false;
    }

    if (doesToolExist(board, StarTool.class, position.move(-1, -1))
        && doesToolExist(board, StarTool.class, position.move(0, -1))
        && doesToolExist(board, StarTool.class, position.move(+1, -1))) {
      return false;
    }

    if (doesToolExist(board, StarTool.class, position.move(-1, +1))
        && doesToolExist(board, StarTool.class, position.move(0, +1))
        && doesToolExist(board, StarTool.class, position.move(+1, +1))) {
      return false;
    }

    board.setTool(position, new StarTool());
    return true;
  }
}
