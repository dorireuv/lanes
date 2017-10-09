package com.dorireuv.lanes.com.dorireuv.lanes.core.game;

import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.CompanyDefinitions;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.GalacticBombEventDefinitions;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.Players;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.GalacticBombEventDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.Bank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.CompanyTopHolderFinder;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class Game {

  private final List<Player> players;
  private final ImmutableList<CompanyDefinition> companyDefinitions;
  private final List<GalacticBombEventDefinition> galacticBombEventDefinitions;
  private Board board;
  private final Bank bank;
  private final Map<CompanyDefinition, Company> companies;

  @Inject
  public Game(
      @Players ImmutableList<Player> players,
      @CompanyDefinitions ImmutableList<CompanyDefinition> companyDefinitions,
      @GalacticBombEventDefinitions
          ImmutableList<GalacticBombEventDefinition> galacticBombEventDefinitions,
      Board board,
      Bank bank) {
    this.players = players;
    this.companyDefinitions = companyDefinitions;
    this.galacticBombEventDefinitions = galacticBombEventDefinitions;
    this.board = board;
    this.bank = bank;
    this.companies = new HashMap<>();
    for (CompanyDefinition companyDefinition : companyDefinitions) {
      this.companies.put(companyDefinition, new Company(companyDefinition));
    }
  }

  public List<Player> getPlayers() {
    return players;
  }

  public Company getCompany(CompanyDefinition companyDefinition) {
    return this.companies.get(companyDefinition);
  }

  public Map<CompanyDefinition, Company> getCompanies() {
    return companies;
  }

  public ImmutableList<CompanyDefinition> getCompanyDefinitions() {
    return companyDefinitions;
  }

  public List<GalacticBombEventDefinition> getGalacticBombEventDefinitions() {
    return galacticBombEventDefinitions;
  }

  public boolean doesFreeCompanyExist() {
    for (Company company : companies.values()) {
      if (company.getSize() == 0) {
        return true;
      }
    }

    return false;
  }

  public int getCompanyTopHolder(Company company) {
    return new CompanyTopHolderFinder(company, players).find();
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public Bank getBank() {
    return bank;
  }
}
