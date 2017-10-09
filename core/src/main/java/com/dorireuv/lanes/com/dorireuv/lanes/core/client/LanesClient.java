package com.dorireuv.lanes.com.dorireuv.lanes.core.client;

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.dorireuv.lanes.com.dorireuv.lanes.core.IllegalMoveException;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Lanes;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Phase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.ImmutableBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.ImmutablePlayer;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.turn.Turn;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

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

  public void doMove(Position position) throws IllegalMoveException {
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
  // players
  // ------------------------------------------------------------------------
  public ImmutableList<ImmutablePlayer> getPlayers() {
    return lanes
        .getGame()
        .getPlayers()
        .stream()
        .map(Player::immutableCopy)
        .collect(toImmutableList());
  }

  public ImmutablePlayer getCurrentPlayer() {
    return getPlayers().get(lanes.getTurnIterator().peek().getPlayerIndex());
  }

  public int getCompanyTopHolder(CompanyDefinition companyDefinition) {
    return lanes.getGame().getCompanyTopHolder(getCompany(companyDefinition));
  }

  // ------------------------------------------------------------------------
  // company info
  // ------------------------------------------------------------------------
  public ImmutableList<CompanyDefinition> getCompanyDefinitions() {
    return lanes.getGame().getCompanyDefinitions();
  }

  public int getCompanyValue(CompanyDefinition companyDefinition) {
    return getCompany(companyDefinition).getValue();
  }

  // ------------------------------------------------------------------------
  // game info
  // ------------------------------------------------------------------------
  public Phase getCurrentPhase() {
    return lanes.getCurrentPhase();
  }

  public ImmutableBoard getBoard() {
    return lanes.getBoard().immutableCopy();
  }

  // ------------------------------------------------------------------------
  // turn info
  // ------------------------------------------------------------------------
  public Turn getCurrentTurn() {
    return lanes.getTurnIterator().peek();
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
  private Company getCompany(CompanyDefinition companyDefinition) {
    return lanes.getGame().getCompany(companyDefinition);
  }
}
