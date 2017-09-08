package com.dorireuv.lanes.com.dorireuv.lanes.core.game;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.GalacticBombEventDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.Bank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.CompanyTopHolderFinder;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

  private final List<Player> players;
  private final List<CompanyDefinition> companyDefinitions;
  private final List<GalacticBombEventDefinition> galacticBombEventDefinitions;
  private final Board board;
  private final Bank bank;
  private final Map<CompanyDefinition, Company> companies;

  public Game(
      List<Player> players,
      List<CompanyDefinition> companyDefinitions,
      List<GalacticBombEventDefinition> galacticBombEventDefinitions,
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

  public List<CompanyDefinition> getCompanyDefinitions() {
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

  public Bank getBank() {
    return bank;
  }
}
