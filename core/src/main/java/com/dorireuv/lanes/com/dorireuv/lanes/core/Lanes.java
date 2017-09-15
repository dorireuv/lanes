package com.dorireuv.lanes.com.dorireuv.lanes.core;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriberGroup;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.CompanyTopHolderChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.exception.IllegalMoveException;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.Game;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.CheckerActionExecutor;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker.CheckerFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.move.MovesGenerator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.turn.TurnIterator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import java.util.Arrays;
import java.util.List;

public class Lanes {

  private final Game game;
  private final RandomWrapper randomWrapper;
  private final MovesGenerator movesGenerator;
  private final ClientEventSubscriberGroup clientEventSubscriberGroup;
  private final TurnIterator turnIterator;

  private final ActionFactory actionFactory;
  private final CheckerFactory checkerFactory;
  private final CheckerActionExecutor checkerActionExecutor;
  private List<Position> moves;
  private Position currentMove;
  private Phase currentPhase;

  public Lanes(
      Game game,
      RandomWrapper randomWrapper,
      MovesGenerator movesGenerator,
      ClientEventSubscriberGroup clientEventSubscriberGroup,
      TurnIterator turnIterator,
      CheckerActionExecutor checkerActionExecutor) {
    this.game = game;
    this.randomWrapper = randomWrapper;
    this.movesGenerator = movesGenerator;
    this.clientEventSubscriberGroup = clientEventSubscriberGroup;
    this.turnIterator = turnIterator;
    this.checkerActionExecutor = checkerActionExecutor;

    this.actionFactory = new ActionFactory(this);
    this.checkerFactory = new CheckerFactory(this);
    this.moves = null;
    this.currentMove = null;
    this.currentPhase = Phase.GAME_NOT_STARTED;
  }

  public Game getGame() {
    return game;
  }

  public RandomWrapper getRandomWrapper() {
    return randomWrapper;
  }

  public Board getBoard() {
    return game.getBoard();
  }

  public ClientEventSubscriberGroup getClientEventSubscriberGroup() {
    return clientEventSubscriberGroup;
  }

  public TurnIterator getTurnIterator() {
    return turnIterator;
  }

  public ActionFactory getActionFactory() {
    return actionFactory;
  }

  public Phase getCurrentPhase() {
    return currentPhase;
  }

  public Position getCurrentMove() {
    return currentMove;
  }

  public Player getCurrentPlayer() {
    return game.getPlayers().get(turnIterator.getCurrentPlayerIndex());
  }

  // ------------------------------------------------------------------------
  // actions
  // ------------------------------------------------------------------------
  public void startGame() {
    validatePhase(Phase.GAME_NOT_STARTED);
    startTurn();
  }

  public List<Position> getMoves() {
    validatePhase(Phase.TURN_MOVE);
    return moves;
  }

  public void doMove(Position position) throws IllegalMoveException {
    validatePhase(Phase.TURN_MOVE);
    currentMove = position;
    onDoMoveChecks();
    startTrade();
  }

  private void startTrade() {
    currentPhase = Phase.TURN_TRADE;
    if (turnIterator.shouldEndTurn()) {
      endTurn();
    }
  }

  public void buy(CompanyDefinition companyDefinition, int amount) throws IllegalMoveException {
    validatePhase(Phase.TURN_TRADE);
    int value = getCompanyValue(companyDefinition);
    int totalCashNeeded = value * amount;
    int currentPlayerCashMoney = getCurrentPlayer().getCashMoney();
    if (totalCashNeeded > currentPlayerCashMoney) {
      throw new IllegalMoveException("Insufficient funds");
    }

    Company company = game.getCompany(companyDefinition);
    int oldCompanyTopHolder = game.getCompanyTopHolder(company);
    int curNumOfStocks = getCurrentPlayer().getNumOfStocks(company);
    getCurrentPlayer().setCashMoney(currentPlayerCashMoney - totalCashNeeded);
    getCurrentPlayer().setNumOfStocks(company, curNumOfStocks + amount);
    int newCompanyTopHolder = game.getCompanyTopHolder(company);
    if (oldCompanyTopHolder != newCompanyTopHolder) {
      clientEventSubscriberGroup.onCompanyTopHolderChange(
          new CompanyTopHolderChangeEvent(newCompanyTopHolder));
    }
  }

  public void sell(CompanyDefinition companyDefinition, int amount) throws IllegalMoveException {
    validatePhase(Phase.TURN_TRADE);
    int currentPlayerNumOfStocks =
        getCurrentPlayer().getNumOfStocks(game.getCompany(companyDefinition));
    if (amount > currentPlayerNumOfStocks) {
      throw new IllegalMoveException("Not enough stocks");
    }

    Company company = game.getCompany(companyDefinition);
    int oldCompanyTopHolder = game.getCompanyTopHolder(company);
    int value = getCompanyValue(companyDefinition);
    int curCashMoney = getCurrentPlayer().getCashMoney();
    getCurrentPlayer().setNumOfStocks(company, currentPlayerNumOfStocks - amount);
    getCurrentPlayer()
        .setCashMoney(curCashMoney + (int) (value * amount * (1 - Config.getSellCommission())));
    int newCompanyTopHolder = game.getCompanyTopHolder(company);
    if (oldCompanyTopHolder != newCompanyTopHolder) {
      clientEventSubscriberGroup.onCompanyTopHolderChange(
          new CompanyTopHolderChangeEvent(newCompanyTopHolder));
    }
  }

  public void endTurn() throws IllegalMoveException {
    validatePhase(Phase.TURN_TRADE);
    if (turnIterator.hasNext()) {
      startNextTurn();
    } else {
      endGame();
    }
  }

  // ------------------------------------------------------------------------
  // private
  // ------------------------------------------------------------------------
  private void validatePhase(Phase... phases) throws IllegalMoveException {
    if (!Arrays.asList(phases).contains(getCurrentPhase())) {
      throw new IllegalMoveException("Invalid phase: " + getCurrentPhase());
    }
  }

  private int getCompanyValue(CompanyDefinition companyDefinition) {
    return game.getCompany(companyDefinition).getValue();
  }

  private void startNextTurn() {
    turnIterator.next();
    startTurn();
  }

  private void startTurn() {
    moves = movesGenerator.generate(getBoard(), game.doesFreeCompanyExist());
    onTurnStartChecks();
    currentPhase = Phase.TURN_MOVE;
  }

  private void endGame() {
    currentPhase = Phase.GAME_ENDED;
  }

  private void onTurnStartChecks() {
    checkerActionExecutor
        .execute(checkerFactory.createGoldStarDisappearChecker())
        .execute(checkerFactory.createGalacticBombChecker());
  }

  private void onDoMoveChecks() {
    checkerActionExecutor
        .execute(checkerFactory.createBoardChecker())
        .execute(checkerFactory.createPlayerCashMoneyInterestChecker())
        .execute(checkerFactory.createBankCashMoneyInterestChecker())
        .execute(checkerFactory.createDoublePaymentChecker())
        .execute(checkerFactory.createTrapChecker())
        .execute(checkerFactory.createBonusPaymentChecker())
        .execute(checkerFactory.createFreezeChecker());
  }
}
