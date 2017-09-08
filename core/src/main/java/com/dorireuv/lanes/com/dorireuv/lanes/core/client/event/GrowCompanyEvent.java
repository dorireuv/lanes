package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;

@SuppressWarnings("UnusedDeclaration")
public class GrowCompanyEvent implements Event {

  private final CompanyDefinition companyDefinition;
  private final Position position;

  public GrowCompanyEvent(CompanyDefinition companyDefinition, Position position) {
    this.companyDefinition = companyDefinition;
    this.position = position;
  }

  public CompanyDefinition getCompanyDefinition() {
    return companyDefinition;
  }

  public Position getPosition() {
    return position;
  }
}
