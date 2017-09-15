package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.HitEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;

class HitAction extends ActionBase {

  private final Board board;
  private final Position position;

  HitAction(ClientEventSubscriber clientEventSubscriber, Board board, Position position) {
    super(clientEventSubscriber);
    this.board = board;
    this.position = position;
  }

  @Override
  public void doAction() {
    board.getTool(position).setToolType(ToolType.HIT);
    clientEventSubscriber.onHit(new HitEvent(position));
  }
}
