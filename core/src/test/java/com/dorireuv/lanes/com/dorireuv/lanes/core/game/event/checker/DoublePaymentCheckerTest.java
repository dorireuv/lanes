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
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
final class DoublePaymentCheckerTest {

  @Mock private ActionFactory actionFactory;
  @Mock private Tool tool;

  private Player player1;
  private Player player2;
  private List<Player> sortedPlayers;

  @BeforeEach
  void setUp() {
    player1 = new SimplePlayer(0, "A", 1000);
    player2 = new SimplePlayer(1, "A", 2000);
    sortedPlayers = Arrays.asList(player1, player2);
  }

  @Test
  void checkDispatchesEventOnDoublePayment() throws Exception {
    when(tool.isDoublePayment()).thenReturn(true);
    DoublePaymentChecker doublePaymentEventChecker =
        new DoublePaymentChecker(actionFactory, player1, sortedPlayers, tool);
    doublePaymentEventChecker.check();
    verify(actionFactory, times(1)).createDoublePaymentAction();
    verifyZeroInteractions(actionFactory);
  }

  @Test
  void checkDoesNothingOnPlayerWithMostValue() throws Exception {
    when(tool.isDoublePayment()).thenReturn(true);
    DoublePaymentChecker doublePaymentEventChecker =
        new DoublePaymentChecker(actionFactory, player2, sortedPlayers, tool);
    doublePaymentEventChecker.check();
    verify(actionFactory, times(1)).createNullAction();
    verifyZeroInteractions(actionFactory);
  }

  @Test
  void checkDoesNothingOnNonDoublePayment() throws Exception {
    when(tool.isDoublePayment()).thenReturn(false);
    DoublePaymentChecker doublePaymentEventChecker =
        new DoublePaymentChecker(actionFactory, player1, sortedPlayers, tool);
    doublePaymentEventChecker.check();
    verify(actionFactory, times(1)).createNullAction();
    verifyZeroInteractions(actionFactory);
  }
}
