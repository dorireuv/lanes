package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.Action;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;

public class GoldStarDisappearChecker extends CheckerBase {

  private final RandomWrapper randomWrapper;
  private final Board board;

  GoldStarDisappearChecker(
      ActionFactory actionFactory, RandomWrapper randomWrapper, Board board) {
    super(actionFactory);
    this.randomWrapper = randomWrapper;
    this.board = board;
  }

  @Override
  public Action check() {
    for (int i = 0; i < Config.getGoldStarDisappearNumOfRetries(); i++) {
      Position position = randomWrapper.nextPosition(board);
      if (board.getTool(position).getToolType().equals(ToolType.GOLD_STAR)) {
        return actionFactory.createGoldStarDisappearAction(position);
      }
    }

    return actionFactory.createNullAction();
  }
}
