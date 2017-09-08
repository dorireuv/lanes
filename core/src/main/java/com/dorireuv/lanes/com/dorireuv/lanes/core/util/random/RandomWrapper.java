package com.dorireuv.lanes.com.dorireuv.lanes.core.util.random;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;

public interface RandomWrapper {

  int nextInt(int n);

  double nextDouble();

  Position nextPosition(Board board);
}
