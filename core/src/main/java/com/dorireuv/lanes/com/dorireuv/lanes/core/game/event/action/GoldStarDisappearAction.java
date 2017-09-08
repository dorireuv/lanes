package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.GoldStarDisappearEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.EmptyTool;

class GoldStarDisappearAction extends ActionBase {

  private final Board board;
  private final Position position;

  public GoldStarDisappearAction(
      ClientEventSubscriber clientEventSubscriber, Board board, Position position) {
    super(clientEventSubscriber);
    this.board = board;
    this.position = position;
  }

  @Override
  public void doAction() {
    board.setTool(position, new EmptyTool(board.getTool(position)));
    clientEventSubscriber.onGoldStarDisappear(new GoldStarDisappearEvent(position));
  }
}
