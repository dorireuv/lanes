package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
final class ClientEventSubscriberFactoryTest {

  @Mock private ClientEventSubscriber clientEventSubscriber;

  @Test
  void getClientEventSubscriberGroup() throws Exception {
    ClientEventSubscriberGroup clientEventSubscriberGroup =
        ClientEventSubscriberFactory.getClientEventSubscriberGroup();
    clientEventSubscriberGroup.register(clientEventSubscriber);
    clientEventSubscriberGroup.onDoublePayment(new DoublePaymentEvent());
    verify(clientEventSubscriber, times(1)).onDoublePayment(any());
  }
}
