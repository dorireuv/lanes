package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ClientEventSubscriberFactoryTest extends TestBase {
  @Mock
  private ClientEventSubscriber clientEventSubscriber;

  @Test
  public void testGetClientEventSubscriberGroup() throws Exception {
    ClientEventSubscriberGroup clientEventSubscriberGroup =
        ClientEventSubscriberFactory.getClientEventSubscriberGroup();
    clientEventSubscriberGroup.register(clientEventSubscriber);
    clientEventSubscriberGroup.onDoublePayment(new DoublePaymentEvent());
    verify(clientEventSubscriber, times(1)).onDoublePayment(any(DoublePaymentEvent.class));
  }
}
