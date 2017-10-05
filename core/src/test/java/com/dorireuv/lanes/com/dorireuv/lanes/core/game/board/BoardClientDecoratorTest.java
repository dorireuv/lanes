package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class BoardClientDecoratorTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ClientEventSubscriber clientEventSubscriber;

  private BoardClientDecorator boardClientDecorator;

  @Before
  public void setUp() {
    Board board = new SimpleBoard();
    boardClientDecorator = new BoardClientDecorator(board, clientEventSubscriber);
  }

  @Test
  public void setToolDispatchesEvent() throws Exception {
    Position position = Position.create(3, 5);
    Tool tool = Tool.newStarTool();
    boardClientDecorator.setTool(position, tool);
    verify(clientEventSubscriber, times(1)).onBoardChange(any());
    verifyNoMoreInteractions(clientEventSubscriber);
  }
}
