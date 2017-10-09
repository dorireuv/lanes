package com.dorireuv.lanes.com.dorireuv.lanes.core.game.move;

import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.NumOfMoveOptions;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.google.common.collect.ImmutableSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;

public class MovesGenerator {

  private final SingleMoveGenerator singleMoveGenerator;
  private final int numOfMoves;

  @Inject
  public MovesGenerator(SingleMoveGenerator singleMoveGenerator, @NumOfMoveOptions int numOfMoves) {
    this.singleMoveGenerator = singleMoveGenerator;
    this.numOfMoves = numOfMoves;
  }

  public ImmutableSet<Position> generate(Board board, boolean doesFreeCompanyExist) {
    // Deterministic ordering is guaranteed since LinkedHashSet iterates in insertion order.
    Set<Position> positions = new LinkedHashSet<>(numOfMoves);
    while (positions.size() < numOfMoves) {
      Optional<Position> candidate = singleMoveGenerator.generate(board, doesFreeCompanyExist);
      candidate.ifPresent(positions::add);
    }

    return ImmutableSet.copyOf(positions);
  }
}
