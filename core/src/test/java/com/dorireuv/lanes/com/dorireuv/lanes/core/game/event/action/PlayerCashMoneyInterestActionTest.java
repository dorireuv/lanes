package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

public class PlayerCashMoneyInterestActionTest extends TestBase {

  private Company company1;
  private Company company2;
  private PlayerCashMoneyInterestAction playerCashMoneyInterestAction;
  private Player player;

  @Before
  public void setUp() throws Exception {
    CompanyDefinition companyDefinition1 = CompanyDefinition.create('A', "A");
    CompanyDefinition companyDefinition2 = CompanyDefinition.create('B', "B");
    company1 = new Company(companyDefinition1);
    company2 = new Company(companyDefinition2);
    Collection<Company> companies = Arrays.asList(company1, company2);
    player = new SimplePlayer(0, "name", 0);
    playerCashMoneyInterestAction = new PlayerCashMoneyInterestAction(player, companies);
  }

  @Test
  public void testCheck() throws Exception {
    company1.setValue(600);
    company2.setValue(1200);
    player.setNumOfStocks(company1, 100);
    player.setNumOfStocks(company2, 200);
    playerCashMoneyInterestAction.doAction();
    Assert.assertEquals(player.getCashMoney(), 15000);
  }
}
