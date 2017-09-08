package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.BoardChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.StarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class BoardClientDecoratorTest extends TestBase {

  @Mock
  private ClientEventSubscriber clientEventSubscriber;
  private BoardClientDecorator boardClientDecorator;

  @Before
  public void setUp() {
    Board board = new SimpleBoard();
    boardClientDecorator = new BoardClientDecorator(board, clientEventSubscriber);
  }

  @Test
  public void testSetToolDispatchesEvent() throws Exception {
    Position position = Position.create(3, 5);
    Tool tool = new StarTool();
    boardClientDecorator.setTool(position, tool);
    verify(clientEventSubscriber, times(1)).onBoardChange(any(BoardChangeEvent.class));
    verifyNoMoreInteractions(clientEventSubscriber);
  }
}
