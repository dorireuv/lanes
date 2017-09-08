package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.CompanyTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.GoldStarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.HitTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.StarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;

import java.util.List;
import java.util.Map;

class CompanyUpdateAction extends ActionBase {

  private final Board board;
  private final Position position;
  private final Company company;
  private final List<Player> players;

  public CompanyUpdateAction(
      ClientEventSubscriber clientEventSubscriber,
      Board board,
      Position position,
      Company company,
      List<Player> players) {
    super(clientEventSubscriber);
    this.board = board;
    this.position = position;
    this.company = company;
    this.players = players;
  }

  private void updateCompanyValue() {
    for (Map.Entry<Position, Tool> entry : board.getToolsAround(position).entrySet()) {
      Tool curTool = entry.getValue();
      if (curTool instanceof GoldStarTool) {
        company.incValue(Config.getCompanyGoldStarValue());
      } else if (curTool instanceof StarTool) {
        company.incValue(Config.getCompanyStarValue());
      } else if (curTool instanceof HitTool) {
        Position curPosition = entry.getKey();
        company.incValue(Config.getCompanyHitValue());
        board.setTool(
            curPosition,
            new CompanyTool(board.getTool(curPosition), company.getCompanyDefinition()));
        company.incSize();
      }
    }
  }

  private void checkForSplit() {
    if (company.getValue() > Config.getCompanySplitValue()) {
      company.setValue(company.getValue() / 2);
      for (Player player : players) {
        int numOfStocks = player.getNumOfStocks(company);
        player.setNumOfStocks(company, numOfStocks * 2);
      }
    }
  }

  @Override
  public void doAction() {
    updateCompanyValue();
    checkForSplit();
  }
}
