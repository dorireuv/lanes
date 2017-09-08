package com.dorireuv.lanes.com.dorireuv.lanes.core.turn;

public class TurnIterator {

  private final int firstPlayerIndex;
  private final int numOfPlayers;
  private int numOfTurns;
  private int currentTurn;
  private int currentPlayerIndex;
  private boolean shouldEndTurn;

  public TurnIterator(int firstPlayerIndex, int numOfPlayers, int numOfTurns) {
    this.firstPlayerIndex = firstPlayerIndex;
    this.numOfPlayers = numOfPlayers;
    this.numOfTurns = numOfTurns;

    this.currentPlayerIndex = firstPlayerIndex;
    this.currentTurn = 1;
  }

  public boolean hasNext() {
    return currentTurn < numOfTurns;
  }

  public void next() {
    shouldEndTurn = false;
    currentPlayerIndex = (currentPlayerIndex + 1) % numOfPlayers;
    currentTurn++;
  }

  public int getFirstPlayerIndex() {
    return firstPlayerIndex;
  }

  public int getCurrentPlayerIndex() {
    return currentPlayerIndex;
  }

  public int getCurrentTurn() {
    return currentTurn;
  }

  public void setNumOfTurns(int numOfTurns) {
    this.numOfTurns = numOfTurns;
  }

  public int getNumOfTurns() {
    return numOfTurns;
  }

  public void setShouldEndTurn() {
    this.shouldEndTurn = true;
  }

  public boolean shouldEndTurn() {
    return shouldEndTurn;
  }
}
