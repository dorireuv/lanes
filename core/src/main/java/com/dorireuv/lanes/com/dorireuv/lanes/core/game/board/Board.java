package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ImmutableTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.collect.Immutable2DArray;

public abstract class Board extends ImmutableBoard {

  @Override
  public final Tool getTool(int row, int col) {
    return getBoard().get(row, col);
  }

  @Override
  public final Tool getTool(Position position) {
    return getTool(position.getRow(), position.getCol());
  }

  public void setEmpty(Position position) {
    getTool(position).setToolType(ToolType.EMPTY);
  }

  public void setHit(Position position) {
    getTool(position).setToolType(ToolType.HIT);
  }

  public void setStar(Position position) {
    getTool(position).setToolType(ToolType.STAR);
  }

  public void setGoldStar(Position position) {
    getTool(position).setToolType(ToolType.GOLD_STAR);
  }

  public void setCompany(Position position, CompanyDefinition companyDefinition) {
    getTool(position).setCompanyDefinition(companyDefinition);
  }

  public abstract Immutable2DArray<Tool> getBoard();

  @Override
  public Immutable2DArray<ImmutableTool> immutableBoard() {
    Immutable2DArray.Builder<ImmutableTool> boardBuilder =
        Immutable2DArray.<ImmutableTool>builder(getBoard().rows(), getBoard().cols())
            .setDefaultValue(() -> ImmutableTool.EMPTY);
    for (int i = 0; i < getBoard().rows(); i++) {
      for (int j = 0; j < getBoard().cols(); j++) {
        boardBuilder.put(i, j, getBoard().get(i, j).immutableCopy());
      }
    }

    return boardBuilder.build();
  }

  public final ImmutableBoard immutableCopy() {
    return ImmutableBoard.builder().setImmutableBoard(immutableBoard()).build();
  }
}
