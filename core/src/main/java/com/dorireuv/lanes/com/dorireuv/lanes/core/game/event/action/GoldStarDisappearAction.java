package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.GoldStarDisappearEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;

class GoldStarDisappearAction extends ActionBase {

  private final Board board;
  private final Position position;

  GoldStarDisappearAction(
      ClientEventSubscriber clientEventSubscriber, Board board, Position position) {
    super(clientEventSubscriber);
    this.board = board;
    this.position = position;
  }

  @Override
  public void doAction() {
    board.getTool(position).setToolType(ToolType.EMPTY);
    clientEventSubscriber.onGoldStarDisappear(new GoldStarDisappearEvent(position));
  }
}
