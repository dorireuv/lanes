package com.dorireuv.lanes.com.dorireuv.lanes.core.game.player;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;

public abstract class Player extends ImmutablePlayer {

  public abstract void setCashMoney(int cashMoney);

  public abstract void setNumOfStocks(Company company, int numOfStocks);

  public abstract ImmutablePlayer immutableCopy();
}
