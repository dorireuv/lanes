package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import com.dorireuv.lanes.com.dorireuv.lanes.core.Lanes;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.PlayerNetValueSorter;

import java.util.LinkedList;

public class CheckerFactory {

  private final Lanes lanes;

  public CheckerFactory(Lanes lanes) {
    this.lanes = lanes;
  }

  public Checker createBankCashMoneyInterestChecker() {
    return new BankCashMoneyInterestChecker(lanes.getActionFactory());
  }

  public Checker createBoardChecker() {
    return new BoardChecker(
        lanes.getActionFactory(),
        lanes.getBoard(),
        lanes.getCurrentMove(),
        lanes.getGame().getCompanies());
  }

  public Checker createBonusPaymentChecker() {
    return new BonusPaymentChecker(lanes.getActionFactory(), lanes.getRandomWrapper());
  }

  public Checker createDoublePaymentChecker() {
    return new DoublePaymentChecker(
        lanes.getActionFactory(),
        lanes.getCurrentPlayer(),
        new PlayerNetValueSorter(lanes.getGame().getPlayers()).sort(),
        lanes.getGame().getBoard().getTool(lanes.getCurrentMove()));
  }

  public Checker createFreezeChecker() {
    return new FreezeChecker(
        lanes.getActionFactory(), lanes.getGame().getBoard().getTool(lanes.getCurrentMove()));
  }

  public Checker createGalacticBombChecker() {
    return new GalacticBombChecker(
        lanes.getActionFactory(),
        lanes.getRandomWrapper(),
        new LinkedList<>(lanes.getGame().getCompanies().values()),
        lanes.getGame().getGalacticBombEventDefinitions());
  }

  public Checker createGoldStarDisappearChecker() {
    return new GoldStarDisappearChecker(
        lanes.getActionFactory(), lanes.getRandomWrapper(), lanes.getBoard());
  }

  public Checker createPlayerCashMoneyInterestChecker() {
    return new PlayerCashMoneyInterestChecker(lanes.getActionFactory());
  }

  public Checker createTrapChecker() {
    return new TrapChecker(
        lanes.getActionFactory(),
        lanes.getRandomWrapper(),
        lanes.getGame().getBoard().getTool(lanes.getCurrentMove()));
  }
}
