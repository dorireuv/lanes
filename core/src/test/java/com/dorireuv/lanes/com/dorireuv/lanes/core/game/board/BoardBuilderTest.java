package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import org.junit.Test;

public class BoardBuilderTest extends TestBase {
  @Test
  public void testBuild() throws Exception {
    BoardBuilder boardBuilder = new BoardBuilder();
    int numOfStars = 100;
    int numOfPlayers = 2;
    boardBuilder.buildDefault(numOfStars, numOfPlayers, 0);
  }
}
