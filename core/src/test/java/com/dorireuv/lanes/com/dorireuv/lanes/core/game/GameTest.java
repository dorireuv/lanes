package com.dorireuv.lanes.com.dorireuv.lanes.core.game;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.Bank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.SimpleBank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GameTest {

  private Game game;
  private Player player1;
  private Player player2;

  @Before
  public void setUp() throws Exception {
    player1 = new SimplePlayer(0, "P1", 0);
    player2 = new SimplePlayer(1, "P2", 0);
    Bank bank = new SimpleBank();
    Board board = new SimpleBoard();
    game = new GameBuilder().buildNewDefaultGame(Arrays.asList(player1, player2), board, bank);
  }

  @Test
  public void testDoesFreeCompanyExist() throws Exception {
    List<CompanyDefinition> companyDefinitions = game.getCompanyDefinitions();
    game.getCompany(companyDefinitions.get(0)).setSize(1);
    game.getCompany(companyDefinitions.get(1)).setSize(1);
    Assert.assertTrue(game.doesFreeCompanyExist());
  }

  @Test
  public void testDoesFreeCompanyExistReturnsFalseWhenNoCompanyAvailable() throws Exception {
    for (CompanyDefinition companyDefinition : game.getCompanyDefinitions()) {
      game.getCompany(companyDefinition).setSize(1);
    }

    Assert.assertFalse(game.doesFreeCompanyExist());
  }

  @Test
  public void testGetPlayerWithMostHoldingsInCompany() throws Exception {
    Company company = game.getCompany(game.getCompanyDefinitions().get(0));
    player1.setNumOfStocks(company, 1);
    player2.setNumOfStocks(company, 2);
    int playerIndex = game.getCompanyTopHolder(company);
    Assert.assertEquals(playerIndex, 1);
  }

  @Test
  public void testGetPlayerWithMostHoldingsInCompanyWhenNoHoldingsReturnsMinusOne()
      throws Exception {
    Company company = game.getCompany(game.getCompanyDefinitions().get(0));
    int playerIndex = game.getCompanyTopHolder(company);
    Assert.assertEquals(playerIndex, -1);
  }
}
