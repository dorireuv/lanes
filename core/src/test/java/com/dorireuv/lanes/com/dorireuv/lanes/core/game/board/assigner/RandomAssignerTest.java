package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board.BoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class RandomAssignerTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private RandomWrapper randomWrapper;
  @Mock private BoardAssigner boardAssigner;
  @Mock private Board board;

  @Test
  public void assign() throws Exception {
    when(boardAssigner.assignPosition(eq(board), any(Position.class))).thenReturn(true);
    RandomAssigner randomAssigner = new RandomAssigner(randomWrapper, boardAssigner, board);
    randomAssigner.assign();
    verify(boardAssigner, times(1)).assignPosition(eq(board), any(Position.class));
  }
}
