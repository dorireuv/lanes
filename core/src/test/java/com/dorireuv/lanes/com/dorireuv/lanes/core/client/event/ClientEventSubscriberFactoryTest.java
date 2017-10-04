package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ClientEventSubscriberFactoryTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ClientEventSubscriber clientEventSubscriber;

  @Test
  public void getClientEventSubscriberGroup() throws Exception {
    ClientEventSubscriberGroup clientEventSubscriberGroup =
        ClientEventSubscriberFactory.getClientEventSubscriberGroup();
    clientEventSubscriberGroup.register(clientEventSubscriber);
    clientEventSubscriberGroup.onDoublePayment(new DoublePaymentEvent());
    verify(clientEventSubscriber, times(1)).onDoublePayment(any(DoublePaymentEvent.class));
  }
}
