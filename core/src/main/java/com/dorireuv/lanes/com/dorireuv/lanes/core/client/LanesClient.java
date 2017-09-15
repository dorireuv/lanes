package com.dorireuv.lanes.com.dorireuv.lanes.core.client;

import com.dorireuv.lanes.com.dorireuv.lanes.core.Lanes;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Phase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.exception.IllegalMoveException;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.data.Data;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.PlayerNetValueCalculator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.PlayerStockValueCalculator;
import java.util.List;

@SuppressWarnings("UnusedDeclaration")
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
  public void startGame() throws IllegalMoveException {
    lanes.startGame();
  }

  public List<Position> getMoves() throws IllegalMoveException {
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
  public String getPlayerName(int playerIndex) {
    return lanes.getGame().getPlayers().get(playerIndex).getName();
  }

  public int getCompanyTopHolder(CompanyDefinition companyDefinition) {
    return lanes.getGame().getCompanyTopHolder(getCompany(companyDefinition));
  }

  public int getPlayerCashValue(int playerIndex) {
    return lanes.getGame().getPlayers().get(playerIndex).getCashMoney();
  }

  public int getPlayerStockValue(int playerIndex) {
    return new PlayerStockValueCalculator(lanes.getGame().getPlayers().get(playerIndex)).calc();
  }

  public int getPlayerNetValue(int playerIndex) {
    return new PlayerNetValueCalculator(lanes.getGame().getPlayers().get(playerIndex)).calc();
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

  public Data[][] getBoardData() {
    return lanes.getBoard().getBoardData();
  }

  public Data getBoardData(int row, int col) {
    return lanes.getBoard().getBoardData(row, col);
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
