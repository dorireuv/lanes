package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ImmutableTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.collect.Immutable2DArray;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@AutoValue
public abstract class ImmutableBoard {

  public abstract Immutable2DArray<ImmutableTool> immutableBoard();

  public final int getRows() {
    return immutableBoard().rows();
  }

  public final int getCols() {
    return immutableBoard().cols();
  }

  public ImmutableTool getTool(Position position) {
    return getTool(position.getRow(), position.getCol());
  }

  public ImmutableTool getTool(int row, int col) {
    return immutableBoard().get(row, col);
  }

  public ImmutableTool getToolWithoutBoundProtection(Position position) {
    return getToolWithoutBoundProtection(position.getRow(), position.getCol());
  }

  private ImmutableTool getToolWithoutBoundProtection(int row, int col) {
    if (row < 0 || row >= getRows() || col < 0 || col >= getCols()) {
      return Tool.newEmptyTool();
    }

    return getTool(row, col);
  }

  public ImmutableMap<Position, ImmutableTool> getToolsAround(Position centerPosition) {
    ImmutableList<Position> positions =
        ImmutableList.of(
            centerPosition.move(-1, 0),
            centerPosition.move(+1, 0),
            centerPosition.move(0, -1),
            centerPosition.move(0, +1));

    ImmutableMap.Builder<Position, ImmutableTool> positionToToolMap = ImmutableMap.builder();
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

    public abstract Builder setImmutableBoard(Immutable2DArray<ImmutableTool> newBoard);

    public abstract ImmutableBoard build();
  }
}
