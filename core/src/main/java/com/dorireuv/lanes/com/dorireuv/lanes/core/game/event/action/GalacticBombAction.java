package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.GalacticBombEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.GalacticBombEventDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;

class GalacticBombAction extends ActionBase {

  private final Company company;
  private final GalacticBombEventDefinition galacticBombEventDefinition;

  public GalacticBombAction(
      ClientEventSubscriber clientEventSubscriber,
      Company company,
      GalacticBombEventDefinition galacticBombEventDefinition) {
    super(clientEventSubscriber);
    this.company = company;
    this.galacticBombEventDefinition = galacticBombEventDefinition;
  }

  @Override
  public void doAction() {
    company.incValue((int) (company.getValue() * galacticBombEventDefinition.getEffect()));
    clientEventSubscriber.onGalacticBomb(
        new GalacticBombEvent(company.getCompanyDefinition(), galacticBombEventDefinition));
  }
}
