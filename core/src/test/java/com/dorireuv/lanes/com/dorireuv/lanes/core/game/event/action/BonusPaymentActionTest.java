package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.SimpleBank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class BonusPaymentActionTest extends TestBase {

  @Mock
  private ClientEventSubscriber clientEventSubscriber;

  @Test
  public void testDoAction() {
    int playerCashMoney = 1000;
    int bankCashMoney = 2000;
    SimpleBank bank = new SimpleBank(bankCashMoney);
    Player player = new SimplePlayer(0, "name", playerCashMoney);

    BonusPaymentAction bonusPaymentAction =
        new BonusPaymentAction(clientEventSubscriber, bank, player);
    bonusPaymentAction.doAction();
    assertEquals(player.getCashMoney(), 2000);
    assertEquals(bank.getCashMoney(), 1000);
  }
}
