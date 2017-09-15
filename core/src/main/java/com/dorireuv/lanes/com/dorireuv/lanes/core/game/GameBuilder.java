package com.dorireuv.lanes.com.dorireuv.lanes.core.game;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.PredefinedCompanyLoader;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.GalacticBombEventDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.PredefinedEventLoader;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.Bank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader.FailedToLoadException;
import java.util.List;

public class GameBuilder {

  public Game buildNewDefaultGame(List<Player> players, Board board, Bank bank)
      throws FailedToLoadException {
    List<CompanyDefinition> companies = new PredefinedCompanyLoader().load();
    List<GalacticBombEventDefinition> galacticBombEventDefinitions =
        new PredefinedEventLoader().load();
    return new Game(players, companies, galacticBombEventDefinitions, board, bank);
  }
}
