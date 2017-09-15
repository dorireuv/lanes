package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;

public class DoublePaymentActionTest {

  @Rule
  public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock
  private ClientEventSubscriber clientEventSubscriber;

  @Test
  public void testDoAction() {
    int playerCashMoney = 1000;
    Player player = new SimplePlayer(0, "name", playerCashMoney);

    DoublePaymentAction doublePaymentAction =
        new DoublePaymentAction(clientEventSubscriber, player);
    doublePaymentAction.doAction();
    assertEquals(player.getCashMoney(), 2000);
  }
}
