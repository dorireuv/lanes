package com.dorireuv.lanes.com.dorireuv.lanes.core.game.move;

import static com.google.common.truth.Truth8.assertThat;
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class SingleMoveGeneratorTest {
  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private RandomWrapper randomWrapper;

  private SingleMoveGenerator singleMoveGenerator;
  private Board board;

  @Before
  public void setUp() throws Exception {
    singleMoveGenerator = new SingleMoveGenerator(randomWrapper);
    board = new SimpleBoard();
  }

  @Test
  public void generate_onStar_isEmpty() throws Exception {
    Position position = Position.create(5, 5);
    when(randomWrapper.nextPosition(board)).thenReturn(position);
    board.setTool(position, Tool.newStarTool());

    Optional<Position> candidate = singleMoveGenerator.generate(board, true);

    assertThat(candidate).isEmpty();
  }

  @Test
  public void generate_onEmpty_hasValue() throws Exception {
    Position position = Position.create(5, 5);
    when(randomWrapper.nextPosition(board)).thenReturn(position);

    Optional<Position> candidate = singleMoveGenerator.generate(board, true);

    assertThat(candidate).hasValue(position);
  }

  @Test
  public void generate_noCompanyLeftAndNearStar_isEmpty() throws Exception {
    board.setTool(Position.create(5, 5), Tool.newStarTool());
    when(randomWrapper.nextPosition(board)).thenReturn(Position.create(5, 6));

    Optional<Position> candidate = singleMoveGenerator.generate(board, false);

    assertThat(candidate).isEmpty();
  }
}
