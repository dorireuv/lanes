package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.BoardChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.collect.Immutable2DArray;
import com.google.common.collect.ImmutableMap;

public class BoardClientDecorator extends Board {

  private final Board board;
  private final ClientEventSubscriber clientEventSubscriber;

  public BoardClientDecorator(Board board, ClientEventSubscriber clientEventSubscriber) {
    this.board = board;
    this.clientEventSubscriber = clientEventSubscriber;
  }

  @Override
  public Immutable2DArray<Tool> getBoard() {
    return board.getBoard();
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
  public Tool getToolWithoutBoundProtection(Position position) {
    return board.getToolWithoutBoundProtection(position);
  }

  @Override
  public ImmutableMap<Position, Tool> getToolsAround(Position centerPosition) {
    return board.getToolsAround(centerPosition);
  }

  @Override
  public void setEmpty(Position position) {
    board.setEmpty(position);
    onBoardChange(position);
  }

  @Override
  public void setHit(Position position) {
    board.setHit(position);
    onBoardChange(position);
  }

  @Override
  public void setStar(Position position) {
    board.setStar(position);
    onBoardChange(position);
  }

  @Override
  public void setGoldStar(Position position) {
    board.setGoldStar(position);
    onBoardChange(position);
  }

  @Override
  public void setCompany(Position position, CompanyDefinition companyDefinition) {
    board.setCompany(position, companyDefinition);
    onBoardChange(position);
  }

  private void onBoardChange(Position position) {
    clientEventSubscriber.onBoardChange(new BoardChangeEvent(position, getTool(position)));
  }
}
