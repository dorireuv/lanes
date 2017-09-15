package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board.BoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;

public class RandomAssigner implements Assigner {

  private final RandomWrapper randomWrapper;
  private final BoardAssigner boardAssigner;
  private final Board board;

  public RandomAssigner(RandomWrapper randomWrapper, BoardAssigner boardAssigner, Board board) {
    this.randomWrapper = randomWrapper;
    this.boardAssigner = boardAssigner;
    this.board = board;
  }

  @Override
  public void assign() {
    while (!boardAssigner.assignPosition(board, randomWrapper.nextPosition(board))) ;
  }
}
