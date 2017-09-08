package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.GalacticBombEventDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.Action;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;

import java.util.List;

public class GalacticBombChecker extends CheckerBase {

  private final RandomWrapper randomWrapper;
  private final List<Company> companies;
  private final List<GalacticBombEventDefinition> galacticBombEventDefinitions;

  public GalacticBombChecker(
      ActionFactory actionFactory,
      RandomWrapper randomWrapper,
      List<Company> companies,
      List<GalacticBombEventDefinition> galacticBombEventDefinitions) {
    super(actionFactory);
    this.randomWrapper = randomWrapper;
    this.companies = companies;
    this.galacticBombEventDefinitions = galacticBombEventDefinitions;
  }

  @Override
  public Action check() {
    if (randomWrapper.nextDouble() > Config.getGalacticBombChance()) {
      return actionFactory.createNullAction();
    }

    for (int i = 0; i < Config.getGalacticBombNumOfCompanySearchRetries(); i++) {
      int cmp = randomWrapper.nextInt(companies.size());
      Company company = companies.get(cmp);
      if (company.getSize() == 0) {
        continue;
      }

      int event = randomWrapper.nextInt(galacticBombEventDefinitions.size());
      return actionFactory.createGalacticBombAction(
          company, galacticBombEventDefinitions.get(event));
    }

    return actionFactory.createNullAction();
  }
}
