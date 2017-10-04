package com.dorireuv.lanes.com.dorireuv.lanes.core.game.move;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.google.common.collect.ImmutableSet;
import java.util.Optional;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class MovesGeneratorTest {

  private final SimpleBoard board = new SimpleBoard();

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private SingleMoveGenerator singleMoveGenerator;

  @Test
  public void generate_samePositionIsGeneratedTwice_filtersDuplicate() throws Exception {
    when(singleMoveGenerator.generate(any(), anyBoolean()))
        .thenReturn(Optional.of(Position.create(5, 5)))
        .thenReturn(Optional.of(Position.create(5, 5)))
        .thenReturn(Optional.of(Position.create(5, 6)));
    MovesGenerator movesGenerator = new MovesGenerator(singleMoveGenerator, 2);

    ImmutableSet<Position> moves = movesGenerator.generate(board, false);

    assertThat(moves).containsExactly(Position.create(5, 5), Position.create(5, 6)).inOrder();
  }

  @Test
  public void generate_emptyMove_filtered() throws Exception {
    when(singleMoveGenerator.generate(any(), anyBoolean()))
        .thenReturn(Optional.empty())
        .thenReturn(Optional.of(Position.create(5, 5)));
    MovesGenerator movesGenerator = new MovesGenerator(singleMoveGenerator, 1);

    ImmutableSet<Position> moves = movesGenerator.generate(board, false);

    assertThat(moves).containsExactly(Position.create(5, 5)).inOrder();
  }
}
