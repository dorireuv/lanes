package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;
import javax.annotation.Nullable;

public abstract class Board extends ImmutableBoard {

  private int numOfStars;

  public Board() {
    numOfStars = 0;
  }

  @Override
  public final int getNumOfStars() {
    return numOfStars;
  }

  public void setEmpty(Position position) {
    decNumOfStarsIfNeeded(position);
    getTool(position).setToolType(ToolType.EMPTY);
  }

  public void setHit(Position position) {
    decNumOfStarsIfNeeded(position);
    getTool(position).setToolType(ToolType.HIT);
  }

  public void setStar(Position position) {
    decNumOfStarsIfNeeded(position);
    getTool(position).setToolType(ToolType.STAR);
    incNumOfStars();
  }

  public void setGoldStar(Position position) {
    decNumOfStarsIfNeeded(position);
    getTool(position).setToolType(ToolType.GOLD_STAR);
  }

  public void setCompany(Position position, CompanyDefinition companyDefinition) {
    decNumOfStarsIfNeeded(position);
    getTool(position).setCompanyDefinition(companyDefinition);
  }

  private void decNumOfStarsIfNeeded(Position position) {
    @Nullable Tool oldTool = getTool(position);
    if (oldTool != null) {
      if (oldTool.getToolType().equals(ToolType.STAR)) {
        numOfStars--;
      }
    }
  }

  private void incNumOfStars() {
    numOfStars++;
  }
}
