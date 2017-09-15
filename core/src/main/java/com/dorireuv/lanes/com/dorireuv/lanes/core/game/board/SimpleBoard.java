package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.EmptyTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.data.Data;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBoard implements Board {

  private final int rows;
  private final int cols;
  private final EmptyTool emptyTool;
  private Tool[][] board;

  public SimpleBoard() {
    this.rows = Config.getBoardRows();
    this.cols = Config.getBoardCols();
    this.emptyTool = new EmptyTool();
    this.init();
  }

  @Override
  public int getRows() {
    return rows;
  }

  @Override
  public int getCols() {
    return cols;
  }

  private void init() {
    this.board = new Tool[this.getRows()][this.getCols()];
    for (int row = 0; row < this.getRows(); row++) {
      for (int col = 0; col < this.getCols(); col++) {
        this.setTool(Position.create(row, col), new EmptyTool());
      }
    }
  }

  @Override
  public Tool getTool(Position position) {
    return getTool(position.getRow(), position.getCol());
  }

  @Override
  public Tool getTool(int row, int col) {
    return board[row][col];
  }

  @Override
  public Tool getToolWithoutBoundProtection(Position position) {
    return getToolWithoutBoundProtection(position.getRow(), position.getCol());
  }

  private Tool getToolWithoutBoundProtection(int row, int col) {
    if (row < 0 || row >= getRows() || col < 0 || col >= getCols()) {
      return emptyTool;
    }

    return getTool(row, col);
  }

  @Override
  public void setTool(Position position, Tool tool) {
    this.board[position.getRow()][position.getCol()] = tool;
  }

  private boolean isPositionValid(Position position) {
    return (position.getRow() >= 0
        && position.getRow() < getRows()
        && position.getCol() >= 0
        && position.getCol() < getCols());
  }

  @Override
  public Map<Position, Tool> getToolsAround(Position centerPosition) {
    List<Position> positions =
        Arrays.asList(
            centerPosition.move(-1, 0),
            centerPosition.move(+1, 0),
            centerPosition.move(0, -1),
            centerPosition.move(0, +1));

    Map<Position, Tool> positionToToolMap = new HashMap<>();
    for (Position position : positions) {
      if (isPositionValid(position)) {
        positionToToolMap.put(position, getTool(position));
      }
    }

    return positionToToolMap;
  }

  @Override
  public Data[][] getBoardData() {
    int rows = getRows();
    int cols = getCols();
    Data boardData[][] = new Data[rows][cols];
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        boardData[row][col] = getTool(row, col).getData();
      }
    }

    return boardData;
  }

  @Override
  public Data getBoardData(int row, int col) {
    return getTool(row, col).getData();
  }

  @Override
  public boolean equals(Object other) {
    Board otherBoard = (Board) other;
    int rows = getRows();
    int cols = getCols();
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        if (!getTool(row, col).equals(otherBoard.getTool(row, col))) {
          return false;
        }
      }
    }

    return true;
  }
}
