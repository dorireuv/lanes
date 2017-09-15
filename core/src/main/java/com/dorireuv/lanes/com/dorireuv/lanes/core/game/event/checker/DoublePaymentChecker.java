package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.Action;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import java.util.List;

public class DoublePaymentChecker extends CheckerBase {

  private final Player currentPlayer;
  private final List<Player> sortedPlayers;
  private final Tool tool;

  public DoublePaymentChecker(
      ActionFactory actionFactory, Player currentPlayer, List<Player> sortedPlayers, Tool tool) {
    super(actionFactory);
    this.currentPlayer = currentPlayer;
    this.sortedPlayers = sortedPlayers;
    this.tool = tool;
  }

  @Override
  public Action check() {
    if (currentPlayer == sortedPlayers.get(sortedPlayers.size() - 1)) {
      return actionFactory.createNullAction();
    }
    if (!tool.isDoublePayment()) {
      return actionFactory.createNullAction();
    }

    return actionFactory.createDoublePaymentAction();
  }
}
