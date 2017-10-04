package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import static org.junit.Assert.assertEquals;

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

public class TrapActionTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ClientEventSubscriber clientEventSubscriber;
  @Mock private TurnIterator turnIterator;

  @Test
  public void doAction() {
    int playerCashMoney = 1000;
    int bankCashMoney = 2000;
    SimpleBank bank = new SimpleBank(bankCashMoney);
    Player player = new SimplePlayer(0, "name", playerCashMoney);

    TrapAction trapAction = new TrapAction(clientEventSubscriber, turnIterator, bank, player);
    trapAction.doAction();
    assertEquals(player.getCashMoney(), 0);
    assertEquals(bank.getCashMoney(), 3000);
  }
}
