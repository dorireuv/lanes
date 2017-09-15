package com.dorireuv.lanes.com.dorireuv.lanes.core.game.player;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import java.util.Map;

public interface Player {

  int getIndex();

  String getName();

  int getCashMoney();

  void setCashMoney(int cashMoney);

  Map<Company, Integer> getHoldings();

  int getNumOfStocks(Company company);

  void setNumOfStocks(Company company, int numOfStocks);
}
