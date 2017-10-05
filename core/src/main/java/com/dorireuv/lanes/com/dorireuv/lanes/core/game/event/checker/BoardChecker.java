package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.Action;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionGroup;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BoardChecker extends CheckerBase {

  private final Board board;
  private final Position position;
  private final Map<CompanyDefinition, Company> companies;

  BoardChecker(
      ActionFactory actionFactory,
      Board board,
      Position position,
      Map<CompanyDefinition, Company> companies) {
    super(actionFactory);
    this.board = board;
    this.position = position;
    this.companies = companies;
  }

  private Map<Position, Tool> getPositionToToolMap() {
    return board.getToolsAround(position);
  }

  private boolean isAllEmpty() {
    for (Tool tool : getPositionToToolMap().values()) {
      if (!tool.getToolType().equals(ToolType.EMPTY)) {
        return false;
      }
    }

    return true;
  }

  private Map<CompanyDefinition, Position> getCompanyDefinitionToPositionMap() {
    return getPositionToToolMap()
        .entrySet()
        .stream()
        .filter(e -> e.getValue().getCompanyDefinition().isPresent())
        .collect(
            toMap(
                e -> e.getValue().getCompanyDefinition().get(), Map.Entry::getKey, (p1, p2) -> p1));
  }

  private List<Company> getMergeCompanies(
      Map<CompanyDefinition, Position> companyDefinitionToPositionMap) {
    return companyDefinitionToPositionMap.keySet().stream().map(companies::get).collect(toList());
  }

  private void sortCompaniesFromLargestToSmallest(List<Company> companies) {
    new CompanySorter().sort(companies);
  }

  @Override
  public Action check() {
    if (isAllEmpty()) {
      return actionFactory.createHitAction();
    }

    Map<CompanyDefinition, Position> companyDefinitionToPositionMap =
        getCompanyDefinitionToPositionMap();
    int numOfDifferentCompanies = companyDefinitionToPositionMap.size();
    if (numOfDifferentCompanies == 0) {
      List<CompanyDefinition> companyDefinitions = new LinkedList<>(companies.keySet());
      Collections.sort(companyDefinitions);
      Company firstNonEmptyCompany = null;
      for (CompanyDefinition companyDefinition : companyDefinitions) {
        firstNonEmptyCompany = companies.get(companyDefinition);
        if (firstNonEmptyCompany.getSize() == 0) {
          break;
        }
      }

      return new ActionGroup(
          actionFactory.createCreateCompanyAction(firstNonEmptyCompany),
          actionFactory.createCompanyUpdateAction(firstNonEmptyCompany));
    }

    if (numOfDifferentCompanies == 1) {
      CompanyDefinition companyDefinition =
          companyDefinitionToPositionMap.keySet().toArray(new CompanyDefinition[1])[0];
      Company company = companies.get(companyDefinition);
      return new ActionGroup(
          actionFactory.createGrowCompanyAction(company),
          actionFactory.createCompanyUpdateAction(company));
    }

    List<Company> mergeCompanies = getMergeCompanies(companyDefinitionToPositionMap);
    sortCompaniesFromLargestToSmallest(mergeCompanies);
    ActionGroup actionGroup = new ActionGroup();
    Company mergedIntoCompany = mergeCompanies.remove(0);
    for (Company mergedCompany : mergeCompanies) {
      actionGroup.addAction(
          actionFactory.createMergeCompanyAction(mergedIntoCompany, mergedCompany));
      actionGroup.addAction(actionFactory.createCompanyUpdateAction(mergedIntoCompany));
    }

    return actionGroup;
  }
}
