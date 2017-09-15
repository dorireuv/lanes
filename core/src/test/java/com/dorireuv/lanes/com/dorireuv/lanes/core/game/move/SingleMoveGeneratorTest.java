package com.dorireuv.lanes.com.dorireuv.lanes.core.game.move;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.StarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class SingleMoveGeneratorTest {
  @Rule
  public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock
  private RandomWrapper randomWrapper;

  @Test
  public void testGenerateReturnsNullOnStar() throws Exception {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    when(randomWrapper.nextPosition(board)).thenReturn(position);
    board.setTool(position, new StarTool());

    SingleMoveGenerator singleMoveGenerator = new SingleMoveGenerator(randomWrapper);
    Position candidate = singleMoveGenerator.generate(board, true);
    assertNull(candidate);
  }

  @Test
  public void testGenerate() throws Exception {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    when(randomWrapper.nextPosition(board)).thenReturn(position);

    SingleMoveGenerator singleMoveGenerator = new SingleMoveGenerator(randomWrapper);
    Position candidate = singleMoveGenerator.generate(board, true);
    assertEquals(candidate, position);
  }

  @Test
  public void testGenerateWithNoCompanyLeft() throws Exception {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    when(randomWrapper.nextPosition(board)).thenReturn(position);
    board.setTool(position.move(0, 1), new StarTool());

    SingleMoveGenerator singleMoveGenerator = new SingleMoveGenerator(randomWrapper);
    Position candidate = singleMoveGenerator.generate(board, false);
    assertNull(candidate);
  }
}
