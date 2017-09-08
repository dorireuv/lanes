package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.CompanyTopHolderChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.CreateCompanyEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.CompanyTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;

class CreateCompanyAction extends ActionBase {

  private final Player player;
  private final Board board;
  private final Position position;
  private final Company company;

  public CreateCompanyAction(
      ClientEventSubscriber clientEventSubscriber,
      Player player,
      Board board,
      Position position,
      Company company) {
    super(clientEventSubscriber);
    this.player = player;
    this.board = board;
    this.position = position;
    this.company = company;
  }

  private void updateBoard() {
    board.setTool(
        position, new CompanyTool(board.getTool(position), company.getCompanyDefinition()));
    company.incSize();
    company.incValue(Config.getCompanyHitValue());
  }

  private void updateCurrentPlayerHoldings() {
    player.setNumOfStocks(company, Config.getCompanyNumOfStocksOnCreation());
    clientEventSubscriber.onCompanyTopHolderChange(
        new CompanyTopHolderChangeEvent(player.getIndex()));
  }

  @Override
  public void doAction() {
    updateBoard();
    updateCurrentPlayerHoldings();
    clientEventSubscriber.onCreateCompany(new CreateCompanyEvent(company.getCompanyDefinition()));
  }
}
