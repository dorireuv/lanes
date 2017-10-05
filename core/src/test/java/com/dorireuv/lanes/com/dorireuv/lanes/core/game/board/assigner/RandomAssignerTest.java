package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board.BoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class RandomAssignerTest {

  @Mock private RandomWrapper randomWrapper;
  @Mock private BoardAssigner boardAssigner;
  @Mock private Board board;

  @Test
  void assign() throws Exception {
    when(boardAssigner.assignPosition(eq(board), any())).thenReturn(true);
    RandomAssigner randomAssigner = new RandomAssigner(randomWrapper, boardAssigner, board);
    randomAssigner.assign();
    verify(boardAssigner, times(1)).assignPosition(eq(board), any());
  }
}
