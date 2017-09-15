package com.dorireuv.lanes.com.dorireuv.lanes.core.game.move;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class MovesGeneratorTest {
  @Rule
  public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock
  private SingleMoveGenerator singleMoveGenerator;

  @Test
  public void testGenerate() throws Exception {
    MovesGenerator movesGenerator = new MovesGenerator(singleMoveGenerator, 2);
    Board board = new SimpleBoard();
    Position position1 = Position.create(5, 5);
    Position position2 = Position.create(5, 6);
    boolean freeCompanyExist = false;
    when(singleMoveGenerator.generate(board, freeCompanyExist))
        .thenReturn(null, position1, position1, position2);
    List<Position> moves = movesGenerator.generate(board, freeCompanyExist);
    Assert.assertEquals(moves, Arrays.asList(position1, position2));
  }
}
