package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;

public class ClientEventSubscriberFactory {
  public static ClientEventSubscriberGroup getClientEventSubscriberGroup() {
    return (ClientEventSubscriberGroup)
        Proxy.newProxyInstance(
            ClientEventSubscriberGroup.class.getClassLoader(),
            new Class[] {ClientEventSubscriberGroup.class},
            new InvocationHandler() {

              private final List<ClientEventSubscriber> clientEventSubscribers = new LinkedList<>();

              private void register(ClientEventSubscriber clientEventSubscriber) {
                clientEventSubscribers.add(clientEventSubscriber);
              }

              @Override
              public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("register")) {
                  this.register((ClientEventSubscriber) args[0]);
                  return null;
                }

                List<Object> res = new LinkedList<>();
                for (ClientEventSubscriber clientEventSubscriber : clientEventSubscribers) {
                  res.add(method.invoke(clientEventSubscriber, args));
                }
                return res;
              }
            });
  }
}
