package com.dorireuv.lanes.com.dorireuv.lanes.core.game.player;

import static org.junit.Assert.assertEquals;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import org.junit.jupiter.api.Test;

final class PlayerNetValueCalculatorTest {
  @Test
  void calc() throws Exception {
    Player player = new SimplePlayer(0, "P1", 0);
    player.setCashMoney(1000);
    CompanyDefinition companyDefinition1 = CompanyDefinition.create('A', "A");
    CompanyDefinition companyDefinition2 = CompanyDefinition.create('B', "B");
    Company company1 = new Company(companyDefinition1);
    Company company2 = new Company(companyDefinition2);
    company1.setValue(1500);
    player.setNumOfStocks(company1, 2);
    company2.setValue(2000);
    player.setNumOfStocks(company2, 3);

    PlayerNetValueCalculator playerNetValueCalculator = new PlayerNetValueCalculator(player);
    int netValue = playerNetValueCalculator.calc();
    assertEquals(netValue, 10000);
  }
}
