package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;

import java.util.Map;

@SuppressWarnings("UnusedDeclaration")
public class MergeCompanyEvent implements Event {

  private final Position position;
  private final CompanyDefinition mergedIntoCompanyDefinition;
  private final CompanyDefinition mergedCompanyDefinition;
  private Map<Integer, MergeInfo> mergeInfoPerPlayer;

  public MergeCompanyEvent(
      Position position,
      CompanyDefinition mergedIntoCompanyDefinition,
      CompanyDefinition mergedCompanyDefinition,
      Map<Integer, MergeInfo> mergeInfoPerPlayer) {
    this.position = position;
    this.mergedIntoCompanyDefinition = mergedIntoCompanyDefinition;
    this.mergedCompanyDefinition = mergedCompanyDefinition;
    this.mergeInfoPerPlayer = mergeInfoPerPlayer;
  }

  public Position getPosition() {
    return position;
  }

  public CompanyDefinition getMergedIntoCompanyDefinition() {
    return mergedIntoCompanyDefinition;
  }

  public CompanyDefinition getMergedCompanyDefinition() {
    return mergedCompanyDefinition;
  }

  public Map<Integer, MergeInfo> getMergeInfoPerPlayer() {
    return mergeInfoPerPlayer;
  }

  public static class MergeInfo {
    private int mergedCompanyStocks;
    private int mergedCompanyNewStocks;
    private int mergedIntoCompanyStocks;
    private int bonus;

    public MergeInfo() {
      mergedCompanyStocks = 0;
      mergedCompanyNewStocks = 0;
      mergedIntoCompanyStocks = 0;
      bonus = 0;
    }

    public int getMergedCompanyStocks() {
      return mergedCompanyStocks;
    }

    public void setMergedCompanyStocks(int mergedCompanyStocks) {
      this.mergedCompanyStocks = mergedCompanyStocks;
    }

    public int getMergedCompanyNewStocks() {
      return mergedCompanyNewStocks;
    }

    public void setMergedCompanyNewStocks(int mergedCompanyNewStocks) {
      this.mergedCompanyNewStocks = mergedCompanyNewStocks;
    }

    public int getMergedIntoCompanyStocks() {
      return mergedIntoCompanyStocks;
    }

    public void setMergedIntoCompanyStocks(int mergedIntoCompanyStocks) {
      this.mergedIntoCompanyStocks = mergedIntoCompanyStocks;
    }

    public int getBonus() {
      return bonus;
    }

    public void setBonus(int bonus) {
      this.bonus = bonus;
    }
  }
}
