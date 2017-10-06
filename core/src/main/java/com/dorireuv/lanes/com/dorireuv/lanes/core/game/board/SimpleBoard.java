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

  @Override
  public int getRows() {
    return board.rows();
  }

  @Override
  public int getCols() {
    return board.cols();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private final Immutable2DArray.Builder<Tool> board;

    private Builder() {
      board =
          Immutable2DArray.<Tool>builder(Config.getBoardRows(), Config.getBoardCols())
              .setDefaultValue(Tool::newEmptyTool);
    }

    public Builder put(Position position, Tool tool) {
      board.put(position.getRow(), position.getCol(), tool);
      return this;
    }

    public SimpleBoard build() {
      return new SimpleBoard(board.build());
    }
  }
}
