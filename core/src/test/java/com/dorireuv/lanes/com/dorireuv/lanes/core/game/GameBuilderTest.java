package com.dorireuv.lanes.com.dorireuv.lanes.core.game;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.Bank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.SimpleBank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class GameBuilderTest {

  @Test
  public void buildNewDefaultGame() throws Exception {
    GameBuilder gameBuilder = new GameBuilder();
    Player player1 = new SimplePlayer(0, "P1", 0);
    Player player2 = new SimplePlayer(1, "P2", 0);
    List<Player> players = Arrays.asList(player1, player2);
    Bank bank = new SimpleBank();
    Board board = new SimpleBoard();
    Game game = gameBuilder.buildNewDefaultGame(players, board, bank);
    Assert.assertNotNull(game);
  }
}
