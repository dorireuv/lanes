package com.dorireuv.lanes.com.dorireuv.lanes.core.game;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.JsonStringCompanyLoader;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.GalacticBombEventDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.JsonStringEventLoader;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.Bank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.google.common.collect.ImmutableList;
import java.util.List;

public class GameBuilder {

  public Game buildNewDefaultGame(List<Player> players, Board board, Bank bank) {
    // TODO(dorireuv): remove this class.
    ImmutableList<CompanyDefinition> companies = JsonStringCompanyLoader.loadPredefined();
    ImmutableList<GalacticBombEventDefinition> galacticBombEventDefinitions =
        JsonStringEventLoader.loadPredefined();
    return new Game(
        ImmutableList.copyOf(players), companies, galacticBombEventDefinitions, board, bank);
  }
}
