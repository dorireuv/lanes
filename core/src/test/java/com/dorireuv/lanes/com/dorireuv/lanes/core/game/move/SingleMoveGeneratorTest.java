package com.dorireuv.lanes.com.dorireuv.lanes.core.game.move;

import static com.google.common.truth.Truth8.assertThat;
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import java.util.Optional;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
final class SingleMoveGeneratorTest {

  @Mock private RandomWrapper randomWrapper;

  private SingleMoveGenerator singleMoveGenerator;
  private Board board;

  @BeforeEach
  void setUp() throws Exception {
    singleMoveGenerator = new SingleMoveGenerator(randomWrapper);
    board = new SimpleBoard();
  }

  @Test
  void generate_onStar_isEmpty() throws Exception {
    Position position = Position.create(5, 5);
    when(randomWrapper.nextPosition(board)).thenReturn(position);
    board.setStar(position);

    Optional<Position> candidate = singleMoveGenerator.generate(board, true);

    assertThat(candidate).isEmpty();
  }

  @Test
  void generate_onEmpty_hasValue() throws Exception {
    Position position = Position.create(5, 5);
    when(randomWrapper.nextPosition(board)).thenReturn(position);

    Optional<Position> candidate = singleMoveGenerator.generate(board, true);

    assertThat(candidate).hasValue(position);
  }

  @Test
  void generate_noCompanyLeftAndNearStar_isEmpty() throws Exception {
    board.setStar(Position.create(5, 5));
    when(randomWrapper.nextPosition(board)).thenReturn(Position.create(5, 6));

    Optional<Position> candidate = singleMoveGenerator.generate(board, false);

    assertThat(candidate).isEmpty();
  }
}
