package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

public class PlayerNetValueChangeEvent implements Event {

  private final int playerIndex;
  private final int netValue;

  public PlayerNetValueChangeEvent(int playerIndex, int netValue) {
    this.playerIndex = playerIndex;
    this.netValue = netValue;
  }

  public int getPlayerIndex() {
    return playerIndex;
  }

  public int getNetValue() {
    return netValue;
  }
}
