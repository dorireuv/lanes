package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBoard implements Board {

  private final int rows;
  private final int cols;
  private int numOfStars;
  private final Tool emptyTool;
  private Tool[][] board;

  public SimpleBoard() {
    this.rows = Config.getBoardRows();
    this.cols = Config.getBoardCols();
    this.numOfStars = 0;
    this.emptyTool = Tool.newEmptyTool();
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

  @Override
  public int getNumOfStars() {
    return numOfStars;
  }

  private void init() {
    this.board = new Tool[this.getRows()][this.getCols()];
    for (int row = 0; row < this.getRows(); row++) {
      for (int col = 0; col < this.getCols(); col++) {
        this.setTool(Position.create(row, col), Tool.newEmptyTool());
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
    Tool oldTool = this.board[position.getRow()][position.getCol()];
    if (oldTool != null) {
      if (oldTool.getToolType().equals(ToolType.STAR)) {
        numOfStars--;
      }
    }
    if (tool.getToolType().equals(ToolType.STAR)) {
      numOfStars++;
    }
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
}
