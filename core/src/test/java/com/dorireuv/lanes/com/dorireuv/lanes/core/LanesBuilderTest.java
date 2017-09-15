package com.dorireuv.lanes.com.dorireuv.lanes.core;

import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.gen5.api.Assertions.assertThrows;

public class LanesBuilderTest {

  @Test
  public void testBuildDefault() throws Exception {
    new LanesBuilder().buildDefault(Arrays.asList("P1", "P2"), 100, 100, 100);
  }

  @Test
  public void testBuildDefault_onePlayer_throwsException() throws Exception {
    assertThrows(
        IllegalArgumentException.class,
        () -> new LanesBuilder().buildDefault(Arrays.asList("P1"), 100, 100, 100));
  }

  @Test
  public void testBuildDefaultTooManyPlayersThrowsException() throws Exception {
    int maxNumOfPlayers = Config.getMaxNumOfPlayers();
    String[] playersName = new String[maxNumOfPlayers + 1];
    Arrays.fill(playersName, "P");
    assertThrows(IllegalArgumentException.class, () -> new LanesBuilder().buildDefault(Arrays.asList(playersName), 100, 100, 100));
  }
}
