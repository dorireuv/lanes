package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

public interface ClientEventSubscriberGroup extends ClientEventSubscriber {
  void register(ClientEventSubscriber clientEventSubscriber);
}
