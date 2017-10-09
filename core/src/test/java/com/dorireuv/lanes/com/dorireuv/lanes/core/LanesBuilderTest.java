package com.dorireuv.lanes.com.dorireuv.lanes.core;

import static org.junit.gen5.api.Assertions.assertThrows;

import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

final class LanesBuilderTest {

  @Test
  void buildDefault() throws Exception {
    new LanesBuilder().buildDefault(ImmutableList.of("P1", "P2"), 100, 100, 100);
  }

  @Test
  void buildDefault_onePlayer_throwsException() throws Exception {
    assertThrows(
        IllegalArgumentException.class,
        () -> new LanesBuilder().buildDefault(ImmutableList.of("P1"), 100, 100, 100));
  }

  @Test
  void buildDefaultTooManyPlayersThrowsException() throws Exception {
    int maxNumOfPlayers = Config.getMaxNumOfPlayers();
    String[] playersName = new String[maxNumOfPlayers + 1];
    Arrays.fill(playersName, "P");
    assertThrows(
        IllegalArgumentException.class,
        () -> new LanesBuilder().buildDefault(ImmutableList.copyOf(playersName), 100, 100, 100));
  }
}
