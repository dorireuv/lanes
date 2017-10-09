package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import static org.junit.Assert.assertEquals;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
final class DoublePaymentActionTest {

  @Mock private ClientEventSubscriber clientEventSubscriber;

  @Test
  void doAction() {
    int playerCashMoney = 1000;
    Player player = new SimplePlayer(0, "name", playerCashMoney);

    DoublePaymentAction doublePaymentAction =
        new DoublePaymentAction(clientEventSubscriber, player);
    doublePaymentAction.doAction();
    assertEquals(player.getCashMoney(), 2000);
  }
}
