package com.dorireuv.lanes.com.dorireuv.lanes.core.client;

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.dorireuv.lanes.com.dorireuv.lanes.core.Lanes;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Phase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.exception.IllegalMoveException;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.ImmutablePlayer;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;

public final class LanesClient {

  private final Lanes lanes;

  public LanesClient(Lanes lanes) {
    this.lanes = lanes;
  }

  // ------------------------------------------------------------------------
  // general
  // ------------------------------------------------------------------------
  public void register(ClientEventSubscriber clientEventSubscriber) {
    lanes.getClientEventSubscriberGroup().register(clientEventSubscriber);
  }

  // ------------------------------------------------------------------------
  // actions
  // ------------------------------------------------------------------------
  public void nextBoard() throws IllegalMoveException {
    lanes.nextBoard();
  }

  public void acceptBoard() throws IllegalMoveException {
    lanes.acceptBoard();
  }

  public void startGame() throws IllegalMoveException {
    lanes.startGame();
  }

  public ImmutableSet<Position> getMoves() throws IllegalMoveException {
    return lanes.getMoves();
  }

  public void doMove(Position position) {
    lanes.doMove(position);
  }

  public void endTurn() throws IllegalMoveException {
    lanes.endTurn();
  }

  public void buy(CompanyDefinition companyDefinition, int amount) throws IllegalMoveException {
    lanes.buy(companyDefinition, amount);
  }

  public void sell(CompanyDefinition companyDefinition, int amount) throws IllegalMoveException {
    lanes.sell(companyDefinition, amount);
  }

  // ------------------------------------------------------------------------
  // current player info
  // ------------------------------------------------------------------------
  public int getCurrentPlayerHoldingsInCompany(CompanyDefinition companyDefinition) {
    return getCurrentPlayer().getNumOfStocks(getCompany(companyDefinition));
  }

  public int getCurrentPlayerCashMoney() {
    return getCurrentPlayer().getCashMoney();
  }

  public String getCurrentPlayerName() {
    return getCurrentPlayer().getName();
  }

  // ------------------------------------------------------------------------
  // general player info
  // ------------------------------------------------------------------------
  public int getCompanyTopHolder(CompanyDefinition companyDefinition) {
    return lanes.getGame().getCompanyTopHolder(getCompany(companyDefinition));
  }

  public ImmutableList<ImmutablePlayer> getPlayers() {
    return lanes
        .getGame()
        .getPlayers()
        .stream()
        .map(Player::immutableCopy)
        .collect(toImmutableList());
  }

  // ------------------------------------------------------------------------
  // company info
  // ------------------------------------------------------------------------
  public List<CompanyDefinition> getCompanyDefinitions() {
    return lanes.getGame().getCompanyDefinitions();
  }

  public int getCompanyValue(CompanyDefinition companyDefinition) {
    return lanes.getGame().getCompany(companyDefinition).getValue();
  }

  // ------------------------------------------------------------------------
  // game info
  // ------------------------------------------------------------------------
  public Phase getCurrentPhase() {
    return lanes.getCurrentPhase();
  }

  public int getBankCashMoney() {
    return lanes.getGame().getBank().getCashMoney();
  }

  public Board getBoard() {
    return lanes.getBoard();
  }

  // ------------------------------------------------------------------------
  // turn info
  // ------------------------------------------------------------------------
  public int getNumOfPlayers() {
    return lanes.getGame().getPlayers().size();
  }

  public int getCurrentPlayerIndex() {
    return lanes.getTurnIterator().getCurrentPlayerIndex();
  }

  public int getFirstPlayerIndex() {
    return lanes.getTurnIterator().getFirstPlayerIndex();
  }

  public int getCurrentTurn() {
    return lanes.getTurnIterator().getCurrentTurn();
  }

  public void setNumOfTurns(int numOfTurns) {
    lanes.getTurnIterator().setNumOfTurns(numOfTurns);
  }

  public int getNumOfTurns() {
    return lanes.getTurnIterator().getNumOfTurns();
  }

  // ------------------------------------------------------------------------
  // private
  // ------------------------------------------------------------------------
  private Player getCurrentPlayer() {
    return lanes.getGame().getPlayers().get(getCurrentPlayerIndex());
  }

  private Company getCompany(CompanyDefinition companyDefinition) {
    return lanes.getGame().getCompany(companyDefinition);
  }
}
