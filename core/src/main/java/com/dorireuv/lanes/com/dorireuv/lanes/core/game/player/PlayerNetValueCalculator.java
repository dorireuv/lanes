package com.dorireuv.lanes.com.dorireuv.lanes.core.game.player;

public class PlayerNetValueCalculator {
  private final Player player;

  public PlayerNetValueCalculator(Player player) {
    this.player = player;
  }

  public int calc() {
    return new PlayerStockValueCalculator(player).calc() + player.getCashMoney();
  }
}
