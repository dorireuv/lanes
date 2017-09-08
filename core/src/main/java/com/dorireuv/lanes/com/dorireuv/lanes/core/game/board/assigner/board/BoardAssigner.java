package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;

public interface BoardAssigner {
  boolean assignPosition(Board board, Position position);
}
