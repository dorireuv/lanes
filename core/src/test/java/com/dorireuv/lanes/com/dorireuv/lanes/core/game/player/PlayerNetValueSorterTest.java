package com.dorireuv.lanes.com.dorireuv.lanes.core.game.player;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class PlayerNetValueSorterTest {
  @Test
  public void testSort() throws Exception {
    Player player1 = new SimplePlayer(0, "P1", 2000);
    Player player2 = new SimplePlayer(1, "P2", 1000);
    List<Player> players = Arrays.asList(player1, player2);

    PlayerNetValueSorter playerNetValueSorter = new PlayerNetValueSorter(players);
    List<Player> sortedPlayers = playerNetValueSorter.sort();
    Assert.assertEquals(sortedPlayers, Arrays.asList(player2, player1));
  }
}
