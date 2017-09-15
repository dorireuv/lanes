package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.CompanyTopHolderChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.MergeCompanyEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.CompanyTopHolderFinder;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class MergeCompanyAction extends ActionBase {

  private final List<Player> players;
  private final Board board;
  private final Position position;
  private final Company mergedIntoCompany;
  private final Company mergedCompany;
  private CompanyDefinition mergedIntoCompanyDefinition;
  private CompanyDefinition mergedCompanyDefinition;
  private int mergedCompanyTotalNumOfStocks;
  private final Map<Integer, MergeCompanyEvent.MergeInfo> mergeInfoPerPlayer;

  MergeCompanyAction(
      ClientEventSubscriber clientEventSubscriber,
      List<Player> players,
      Board board,
      Position position,
      Company mergedIntoCompany,
      Company mergedCompany) {
    super(clientEventSubscriber);
    this.players = players;
    this.board = board;
    this.position = position;
    this.mergedIntoCompany = mergedIntoCompany;
    this.mergedCompany = mergedCompany;
    this.mergeInfoPerPlayer = new HashMap<>();
  }

  private void initMergedCompanyTotalNumOfStocks() {
    mergedCompanyTotalNumOfStocks = 0;
    for (Player player : players) {
      mergedCompanyTotalNumOfStocks += player.getNumOfStocks(mergedCompany);
    }
  }

  private void init() {
    this.mergedIntoCompanyDefinition = mergedIntoCompany.getCompanyDefinition();
    this.mergedCompanyDefinition = mergedCompany.getCompanyDefinition();
    this.initMergedCompanyTotalNumOfStocks();
  }

  private void updatePlayerHoldings(Player player) {
    int mergedCompanyStocks = player.getNumOfStocks(mergedCompany);
    int mergedIntoCompanyStocks = player.getNumOfStocks(mergedIntoCompany);
    if (mergedCompanyStocks > 0 || mergedIntoCompanyStocks > 0) {
      getMergeInfo(player).setMergedCompanyStocks(mergedCompanyStocks);
      int newMergedCompanyStocks = (int) (mergedCompanyStocks * 0.5 + 0.5);
      getMergeInfo(player).setMergedCompanyNewStocks(newMergedCompanyStocks);
      player.setNumOfStocks(mergedIntoCompany, mergedIntoCompanyStocks + newMergedCompanyStocks);
      getMergeInfo(player).setMergedIntoCompanyStocks(player.getNumOfStocks(mergedIntoCompany));
    }
  }

  private void updatePlayerCashMoney(Player player) {
    if (mergedCompanyTotalNumOfStocks <= 0) {
      return;
    }

    int mergedCompanyStocks = player.getNumOfStocks(mergedCompany);
    if (mergedCompanyStocks > 0) {
      int bonus =
          (int)
              (100
                  * Config.getMergeBonusPercentage()
                  * ((double) mergedCompanyStocks / mergedCompanyTotalNumOfStocks)
                  * mergedIntoCompany.getValue());
      player.setCashMoney(player.getCashMoney() + bonus);
      getMergeInfo(player).setBonus(bonus);
    }
  }

  private void updatePlayers() {
    CompanyTopHolderFinder companyTopHolderFinder =
        new CompanyTopHolderFinder(mergedIntoCompany, players);
    int oldCompanyTopHolder = companyTopHolderFinder.find();
    for (Player player : players) {
      updatePlayerHoldings(player);
      updatePlayerCashMoney(player);
    }
    int newCompanyTopHolder = companyTopHolderFinder.find();
    if (oldCompanyTopHolder != newCompanyTopHolder) {
      clientEventSubscriber.onCompanyTopHolderChange(
          new CompanyTopHolderChangeEvent(newCompanyTopHolder));
    }
  }

  private void updateBoard() {
    for (int row = 0; row < board.getRows(); row++) {
      for (int col = 0; col < board.getCols(); col++) {
        Tool tool = board.getTool(row, col);
        if (tool.getToolType().equals(ToolType.COMPANY)) {
          Optional<CompanyDefinition> companyDefinition = tool.getCompanyDefinition();
          if (companyDefinition.isPresent()) {
            if (companyDefinition.get().equals(mergedCompanyDefinition)) {
              board.getTool(
                  Position.create(row, col)).setCompanyDefinition(mergedIntoCompanyDefinition);
            }
          }
        }
      }
    }

    Tool oldTool = board.getTool(position);
    if (oldTool.getToolType().equals(ToolType.EMPTY)) {
      board.getTool(position).setCompanyDefinition(mergedIntoCompanyDefinition);
      mergedIntoCompany.incSize();
    }
  }

  private void updateCompaniesSize() {
    mergedIntoCompany.setSize(mergedIntoCompany.getSize() + mergedCompany.getSize());
    mergedCompany.setSize(0);
  }

  private void updateMergedIntoCompanyValue() {
    mergedIntoCompany.incValue(mergedCompany.getValue());
  }

  private void clearMergedCompany() {
    mergedCompany.setValue(0);
    for (Player player : players) {
      player.setNumOfStocks(mergedCompany, 0);
    }
  }

  @Override
  public void doAction() {
    init();
    updatePlayers();
    updateBoard();
    updateCompaniesSize();
    updateMergedIntoCompanyValue();
    clearMergedCompany();
    clientEventSubscriber.onMergeCompany(
        new MergeCompanyEvent(
            position, mergedIntoCompanyDefinition, mergedCompanyDefinition, mergeInfoPerPlayer));
  }

  private MergeCompanyEvent.MergeInfo getMergeInfo(Player player) {
    int index = players.indexOf(player);
    if (!mergeInfoPerPlayer.containsKey(index)) {
      mergeInfoPerPlayer.put(index, new MergeCompanyEvent.MergeInfo());
    }

    return mergeInfoPerPlayer.get(index);
  }
}
