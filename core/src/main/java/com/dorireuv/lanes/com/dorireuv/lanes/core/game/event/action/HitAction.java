package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.HitEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.HitTool;

class HitAction extends ActionBase {

  private final Board board;
  private final Position position;

  public HitAction(ClientEventSubscriber clientEventSubscriber, Board board, Position position) {
    super(clientEventSubscriber);
    this.board = board;
    this.position = position;
  }

  @Override
  public void doAction() {
    board.setTool(position, new HitTool(board.getTool(position)));
    clientEventSubscriber.onHit(new HitEvent(position));
  }
}
