package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.turn.TurnIterator;
import org.junit.Test;
import org.mockito.Mock;

public class FreezeActionTest extends TestBase {

  @Mock
  private ClientEventSubscriber clientEventSubscriber;
  @Mock
  private TurnIterator turnIterator;

  @Test
  public void testDoAction() {
    FreezeAction freezeAction = new FreezeAction(clientEventSubscriber, turnIterator);
    freezeAction.doAction();
  }
}
