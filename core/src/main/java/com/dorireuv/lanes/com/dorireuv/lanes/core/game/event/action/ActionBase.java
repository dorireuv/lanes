package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;

abstract class ActionBase implements Action {

  final ClientEventSubscriber clientEventSubscriber;

  ActionBase(ClientEventSubscriber clientEventSubscriber) {
    this.clientEventSubscriber = clientEventSubscriber;
  }
}
