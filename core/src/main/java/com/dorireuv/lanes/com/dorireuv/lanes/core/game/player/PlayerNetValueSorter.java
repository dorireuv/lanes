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
    sortedPlayers.sort(
        (p1, p2) -> {
          int p1Value = p1.getNetValue();
          int p2Value = p2.getNetValue();
          return p1Value - p2Value;
        });
    return sortedPlayers;
  }
}
