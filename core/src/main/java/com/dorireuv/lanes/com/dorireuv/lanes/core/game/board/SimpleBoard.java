package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.collect.Immutable2DArray;

public class SimpleBoard extends Board {

  private final Immutable2DArray<Tool> board;

  public SimpleBoard(Immutable2DArray<Tool> board) {
    this.board = board;
  }

  public SimpleBoard() {
    this.board =
        Immutable2DArray.<Tool>builder(Config.getBoardRows(), Config.getBoardCols())
            .setDefaultValue(Tool::newEmptyTool)
            .build();
  }

  @Override
  public Immutable2DArray<Tool> getBoard() {
    return board;
  }

  public static SimpleBoardBuilder newSimpleBoardBuilder() {
    return new SimpleBoardBuilder();
  }

  public static class SimpleBoardBuilder {

    private final Immutable2DArray.Builder<Tool> board;

    private SimpleBoardBuilder() {
      board =
          Immutable2DArray.<Tool>builder(Config.getBoardRows(), Config.getBoardCols())
              .setDefaultValue(Tool::newEmptyTool);
    }

    public SimpleBoardBuilder put(Position position, Tool tool) {
      board.put(position.getRow(), position.getCol(), tool);
      return this;
    }

    public SimpleBoard build() {
      return new SimpleBoard(board.build());
    }
  }
}
