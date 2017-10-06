package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.SimpleRandomWrapper;
import org.junit.jupiter.api.Test;

class BoardGeneratorTest {
  @Test
  void generate() throws Exception {
    BoardGenerator boardGenerator = new BoardGenerator(new SimpleRandomWrapper(0));
    int numOfStars = 100;
    int numOfPlayers = 2;
    boardGenerator.generate(numOfStars, numOfPlayers);
  }
}
