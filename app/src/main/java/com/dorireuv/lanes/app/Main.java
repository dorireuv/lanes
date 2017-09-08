package com.dorireuv.lanes.app;

import com.dorireuv.lanes.com.dorireuv.lanes.core.LanesBuilder;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.LanesClient;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader.FailedToLoadException;

import java.util.Arrays;

final class Main {

  public static void main(String[] args) {
    LanesBuilder lanesBuilder = new LanesBuilder();
    try {
      LanesClient lanesClient = lanesBuilder.buildDefault(Arrays.asList("P1", "P2"), 100, 100, 100);
      lanesClient.startGame();
    } catch (FailedToLoadException e) {
      e.printStackTrace();
    }
  }
}
