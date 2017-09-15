package com.dorireuv.lanes.com.dorireuv.lanes.core.game.move;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import java.util.LinkedList;
import java.util.List;

public class MovesGenerator {

  private final SingleMoveGenerator singleMoveGenerator;
  private final int numOfMoves;

  public MovesGenerator(SingleMoveGenerator singleMoveGenerator, int numOfMoves) {
    this.singleMoveGenerator = singleMoveGenerator;
    this.numOfMoves = numOfMoves;
  }

  public List<Position> generate(Board board, boolean doesFreeCompanyExist) {
    List<Position> positions = new LinkedList<>();
    while (positions.size() < numOfMoves) {
      Position candidate = singleMoveGenerator.generate(board, doesFreeCompanyExist);
      if (candidate == null) {
        continue;
      }

      boolean alreadyExist = false;
      for (Position position : positions) {
        if (candidate == position) {
          alreadyExist = true;
          break;
        }
      }
      if (alreadyExist) {
        continue;
      }

      positions.add(candidate);
    }

    return positions;
  }
}
