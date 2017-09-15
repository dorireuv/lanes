package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.SimpleBank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import com.dorireuv.lanes.com.dorireuv.lanes.core.turn.TurnIterator;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;

public class HalfTrapActionTest {

  @Rule
  public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock
  private ClientEventSubscriber clientEventSubscriber;
  @Mock
  private TurnIterator turnIterator;

  @Test
  public void testDoAction() {
    int playerCashMoney = 1000;
    int bankCashMoney = 2000;
    SimpleBank bank = new SimpleBank(bankCashMoney);
    Player player = new SimplePlayer(0, "name", playerCashMoney);

    HalfTrapAction halfTrapAction =
        new HalfTrapAction(clientEventSubscriber, turnIterator, bank, player);
    halfTrapAction.doAction();
    assertEquals(player.getCashMoney(), 500);
    assertEquals(bank.getCashMoney(), 3000);
  }
}
