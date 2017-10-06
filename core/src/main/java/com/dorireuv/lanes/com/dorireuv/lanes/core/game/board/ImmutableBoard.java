package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.collect.Immutable2DArray;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@AutoValue
public abstract class ImmutableBoard {

  private static final Tool EMPTY_TOOL = Tool.newEmptyTool();

  public abstract Immutable2DArray<Tool> getBoard();

  public final int getRows() {
    return getBoard().rows();
  }

  public final int getCols() {
    return getBoard().cols();
  }

  public abstract int getNumOfStars();

  public final Tool getTool(Position position) {
    return getTool(position.getRow(), position.getCol());
  }

  public final Tool getTool(int row, int col) {
    return getBoard().get(row, col);
  }

  public Tool getToolWithoutBoundProtection(Position position) {
    return getToolWithoutBoundProtection(position.getRow(), position.getCol());
  }

  private Tool getToolWithoutBoundProtection(int row, int col) {
    if (row < 0 || row >= getRows() || col < 0 || col >= getCols()) {
      return EMPTY_TOOL;
    }

    return getTool(row, col);
  }

  public ImmutableMap<Position, Tool> getToolsAround(Position centerPosition) {
    ImmutableList<Position> positions =
        ImmutableList.of(
            centerPosition.move(-1, 0),
            centerPosition.move(+1, 0),
            centerPosition.move(0, -1),
            centerPosition.move(0, +1));

    ImmutableMap.Builder<Position, Tool> positionToToolMap = ImmutableMap.builder();
    for (Position position : positions) {
      if (isPositionValid(position)) {
        positionToToolMap.put(position, getTool(position));
      }
    }

    return positionToToolMap.build();
  }

  private boolean isPositionValid(Position position) {
    return (position.getRow() >= 0
        && position.getRow() < getRows()
        && position.getCol() >= 0
        && position.getCol() < getCols());
  }

  public static Builder builder() {
    return new AutoValue_ImmutableBoard.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder setBoard(Immutable2DArray<Tool> newBoard);

    public abstract Builder setNumOfStars(int newNumOfStars);

    public abstract ImmutableBoard build();
  }
}
