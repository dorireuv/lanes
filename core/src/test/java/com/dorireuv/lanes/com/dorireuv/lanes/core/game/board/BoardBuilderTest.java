package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.SimpleRandomWrapper;
import org.junit.Test;

public class BoardBuilderTest {
  @Test
  public void build() throws Exception {
    BoardBuilder boardBuilder = new BoardBuilder(new SimpleRandomWrapper(0));
    int numOfStars = 100;
    int numOfPlayers = 2;
    boardBuilder.buildDefault(numOfStars, numOfPlayers);
  }
}
