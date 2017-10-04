package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class DoublePaymentCheckerTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ActionFactory actionFactory;
  @Mock private Tool tool;

  private Player player1;
  private Player player2;
  private List<Player> sortedPlayers;

  @Before
  public void setUp() {
    player1 = new SimplePlayer(0, "A", 1000);
    player2 = new SimplePlayer(1, "A", 2000);
    sortedPlayers = Arrays.asList(player1, player2);
  }

  @Test
  public void checkDispatchesEventOnDoublePayment() throws Exception {
    when(tool.isDoublePayment()).thenReturn(true);
    DoublePaymentChecker doublePaymentEventChecker =
        new DoublePaymentChecker(actionFactory, player1, sortedPlayers, tool);
    doublePaymentEventChecker.check();
    verify(actionFactory, times(1)).createDoublePaymentAction();
    verifyZeroInteractions(actionFactory);
  }

  @Test
  public void checkDoesNothingOnPlayerWithMostValue() throws Exception {
    when(tool.isDoublePayment()).thenReturn(true);
    DoublePaymentChecker doublePaymentEventChecker =
        new DoublePaymentChecker(actionFactory, player2, sortedPlayers, tool);
    doublePaymentEventChecker.check();
    verify(actionFactory, times(1)).createNullAction();
    verifyZeroInteractions(actionFactory);
  }

  @Test
  public void checkDoesNothingOnNonDoublePayment() throws Exception {
    when(tool.isDoublePayment()).thenReturn(false);
    DoublePaymentChecker doublePaymentEventChecker =
        new DoublePaymentChecker(actionFactory, player1, sortedPlayers, tool);
    doublePaymentEventChecker.check();
    verify(actionFactory, times(1)).createNullAction();
    verifyZeroInteractions(actionFactory);
  }
}
