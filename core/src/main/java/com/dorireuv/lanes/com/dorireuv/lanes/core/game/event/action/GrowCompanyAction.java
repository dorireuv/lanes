package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.GrowCompanyEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.CompanyTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;

class GrowCompanyAction extends ActionBase {

  private final Board board;
  private final Company company;
  private final Position position;

  public GrowCompanyAction(
      ClientEventSubscriber clientEventSubscriber,
      Board board,
      Position position,
      Company company) {
    super(clientEventSubscriber);
    this.board = board;
    this.company = company;
    this.position = position;
  }

  @Override
  public void doAction() {
    company.incSize();
    board.setTool(
        position, new CompanyTool(board.getTool(position), company.getCompanyDefinition()));
    clientEventSubscriber.onGrowCompany(
        new GrowCompanyEvent(company.getCompanyDefinition(), position));
  }
}
