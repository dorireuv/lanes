package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class BoardClientDecoratorTest {

  @Mock private ClientEventSubscriber clientEventSubscriber;

  private BoardClientDecorator boardClientDecorator;

  @BeforeEach
  void setUp() {
    Board board = new SimpleBoard();
    boardClientDecorator = new BoardClientDecorator(board, clientEventSubscriber);
  }

  @Test
  void setToolDispatchesEvent() throws Exception {
    Position position = Position.create(3, 5);
    boardClientDecorator.setStar(position);
    verify(clientEventSubscriber, times(1)).onBoardChange(any());
    verifyNoMoreInteractions(clientEventSubscriber);
  }
}
