package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import static org.junit.Assert.assertEquals;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.SimpleBank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
final class BonusPaymentActionTest {

  @Mock private ClientEventSubscriber clientEventSubscriber;

  @Test
  void doAction() {
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
