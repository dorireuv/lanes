package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.turn.TurnIterator;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
final class FreezeActionTest {

  @Mock private ClientEventSubscriber clientEventSubscriber;
  private final TurnIterator turnIterator =
      TurnIterator.newBuilder().numOfPlayers(2).firstPlayerIndex(0).numOfTurns(2).build();

  @Test
  void doAction() {
    FreezeAction freezeAction = new FreezeAction(clientEventSubscriber, turnIterator);
    freezeAction.doAction();
  }
}
