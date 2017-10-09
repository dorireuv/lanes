package com.dorireuv.lanes.com.dorireuv.lanes.core;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.LanesClient;
import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.google.common.collect.ImmutableList;
import java.util.List;

public class LanesBuilder {

  public LanesClient buildDefault(
      List<String> playersName, int numOfTurns, int numOfStars, long randomSeed)
      throws IllegalArgumentException {
    int numOfPlayers = playersName.size();
    validateNumOfPlayers(numOfPlayers);

    Lanes lanes =
        DaggerLanesComponent.builder()
            .randomSeed(randomSeed)
            .numOfPlayers(numOfPlayers)
            .numOfStars(numOfStars)
            .numOfTurns(numOfTurns)
            .playerNames(ImmutableList.copyOf(playersName))
            .build()
            .lanes();
    return new LanesClient(lanes);
  }

  private void validateNumOfPlayers(int numOfPlayers) throws IllegalArgumentException {
    if (numOfPlayers <= 1) {
      throw new IllegalArgumentException(
          String.format("invalid number of players %d <= 1", numOfPlayers));
    }
    int maxNumOfPlayers = Config.getMaxNumOfPlayers();
    if (numOfPlayers > maxNumOfPlayers) {
      throw new IllegalArgumentException(
          String.format("invalid number of players %d > %d", numOfPlayers, maxNumOfPlayers));
    }
  }
}
