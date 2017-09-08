package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.GalacticBombEventDefinition;

@SuppressWarnings("UnusedDeclaration")
public class GalacticBombEvent implements Event {

  private final CompanyDefinition companyDefinition;
  private final GalacticBombEventDefinition galacticBombEventDefinition;

  public GalacticBombEvent(
      CompanyDefinition companyDefinition,
      GalacticBombEventDefinition galacticBombEventDefinition) {
    this.companyDefinition = companyDefinition;
    this.galacticBombEventDefinition = galacticBombEventDefinition;
  }

  public CompanyDefinition getCompanyDefinition() {
    return companyDefinition;
  }

  public GalacticBombEventDefinition getGalacticBombEventDefinition() {
    return galacticBombEventDefinition;
  }
}
