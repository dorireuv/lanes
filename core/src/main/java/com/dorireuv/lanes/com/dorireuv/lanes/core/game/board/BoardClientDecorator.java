package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.BoardChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.data.Data;

import java.util.Map;

public class BoardClientDecorator implements Board {

  private final Board board;
  private final ClientEventSubscriber clientEventSubscriber;

  public BoardClientDecorator(Board board, ClientEventSubscriber clientEventSubscriber) {
    this.board = board;
    this.clientEventSubscriber = clientEventSubscriber;
  }

  @Override
  public int getRows() {
    return board.getRows();
  }

  @Override
  public int getCols() {
    return board.getCols();
  }

  @Override
  public Tool getTool(Position position) {
    return board.getTool(position);
  }

  @Override
  public Tool getTool(int row, int col) {
    return board.getTool(row, col);
  }

  @Override
  public Tool getToolWithoutBoundProtection(Position position) {
    return board.getToolWithoutBoundProtection(position);
  }

  @Override
  public void setTool(Position position, Tool tool) {
    board.setTool(position, tool);
    clientEventSubscriber.onBoardChange(new BoardChangeEvent(position, tool.getData()));
  }

  @Override
  public Map<Position, Tool> getToolsAround(Position centerPosition) {
    return board.getToolsAround(centerPosition);
  }

  @Override
  public Data[][] getBoardData() {
    return board.getBoardData();
  }

  @Override
  public Data getBoardData(int row, int col) {
    return board.getBoardData(row, col);
  }
}
