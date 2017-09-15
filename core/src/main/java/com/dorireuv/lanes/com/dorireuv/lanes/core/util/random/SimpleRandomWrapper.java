package com.dorireuv.lanes.com.dorireuv.lanes.core.util.random;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import java.util.Random;

public class SimpleRandomWrapper implements RandomWrapper {

  private final Random random;

  public SimpleRandomWrapper(long seed) {
    this.random = new Random(seed);
  }

  @Override
  public int nextInt(int n) {
    return this.random.nextInt(n);
  }

  @Override
  public double nextDouble() {
    return this.random.nextDouble();
  }

  @Override
  public Position nextPosition(Board board) {
    return Position.create(nextInt(board.getRows()), nextInt(board.getCols()));
  }
}
