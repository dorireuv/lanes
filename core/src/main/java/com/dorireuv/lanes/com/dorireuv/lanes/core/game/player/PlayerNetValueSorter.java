package com.dorireuv.lanes.com.dorireuv.lanes.core.game.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerNetValueSorter {
  private final List<Player> unsortedPlayers;

  public PlayerNetValueSorter(List<Player> unsortedPlayers) {
    this.unsortedPlayers = unsortedPlayers;
  }

  public List<Player> sort() {
    ArrayList<Player> sortedPlayers = new ArrayList<>(unsortedPlayers);
    sortedPlayers.sort((p1, p2) -> {
      int p1Value = new PlayerNetValueCalculator(p1).calc();
      int p2Value = new PlayerNetValueCalculator(p2).calc();
      return p1Value - p2Value;
    });
    return sortedPlayers;
  }
}
