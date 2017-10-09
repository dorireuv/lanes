package com.dorireuv.lanes.com.dorireuv.lanes.core.game.move;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.google.common.collect.ImmutableSet;
import java.util.Optional;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
final class MovesGeneratorTest {

  private final SimpleBoard board = new SimpleBoard();

  @Mock private SingleMoveGenerator singleMoveGenerator;

  @Test
  void generate_samePositionIsGeneratedTwice_filtersDuplicate() throws Exception {
    when(singleMoveGenerator.generate(any(), anyBoolean()))
        .thenReturn(Optional.of(Position.create(5, 5)))
        .thenReturn(Optional.of(Position.create(5, 5)))
        .thenReturn(Optional.of(Position.create(5, 6)));
    MovesGenerator movesGenerator = new MovesGenerator(singleMoveGenerator, 2);

    ImmutableSet<Position> moves = movesGenerator.generate(board, false);

    assertThat(moves).containsExactly(Position.create(5, 5), Position.create(5, 6)).inOrder();
  }

  @Test
  void generate_emptyMove_filtered() throws Exception {
    when(singleMoveGenerator.generate(any(), anyBoolean()))
        .thenReturn(Optional.empty())
        .thenReturn(Optional.of(Position.create(5, 5)));
    MovesGenerator movesGenerator = new MovesGenerator(singleMoveGenerator, 1);

    ImmutableSet<Position> moves = movesGenerator.generate(board, false);

    assertThat(moves).containsExactly(Position.create(5, 5)).inOrder();
  }
}
