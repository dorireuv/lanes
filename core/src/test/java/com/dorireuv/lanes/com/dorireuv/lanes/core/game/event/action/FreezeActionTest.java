package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.turn.TurnIterator;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class FreezeActionTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ClientEventSubscriber clientEventSubscriber;
  @Mock private TurnIterator turnIterator;

  @Test
  public void doAction() {
    FreezeAction freezeAction = new FreezeAction(clientEventSubscriber, turnIterator);
    freezeAction.doAction();
  }
}
