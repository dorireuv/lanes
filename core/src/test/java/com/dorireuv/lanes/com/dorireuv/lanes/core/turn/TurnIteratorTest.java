package com.dorireuv.lanes.com.dorireuv.lanes.core.turn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurnIteratorTest {

  private TurnIterator turnIterator;

  @BeforeEach
  void setUp() {
    int firstPlayerIndex = 1;
    int numOfPlayers = 3;
    int numOfTurns = 100;
    turnIterator = new TurnIterator(firstPlayerIndex, numOfPlayers, numOfTurns);
  }

  @Test
  void getCurrentTurnOnBeginningReturnsOne() {
    assertEquals(turnIterator.getCurrentTurn(), 1);
  }

  @Test
  void hasNextWhenNoTurnsLeftReturnsFalse() {
    assertEquals(turnIterator.getNumOfTurns(), 100);
    turnIterator.setNumOfTurns(1);
    assertEquals(turnIterator.getNumOfTurns(), 1);
    assertFalse(turnIterator.hasNext());
  }

  @Test
  void getCurrentPlayerIndexOnBeginningReturnsFirstPlayerIndex() {
    assertEquals(turnIterator.getCurrentPlayerIndex(), turnIterator.getFirstPlayerIndex());
  }

  @Test
  void next() {
    turnIterator.next();
    assertEquals(turnIterator.getCurrentTurn(), 2);
    assertEquals(turnIterator.getCurrentPlayerIndex(), 2);

    turnIterator.next();
    assertEquals(turnIterator.getCurrentTurn(), 3);
    assertEquals(turnIterator.getCurrentPlayerIndex(), 0);
  }

  @Test
  void setShouldEndTurnIsFalseAfterNext() {
    assertFalse(turnIterator.shouldEndTurn());
    turnIterator.setShouldEndTurn();
    assertTrue(turnIterator.shouldEndTurn());
    turnIterator.next();
    assertFalse(turnIterator.shouldEndTurn());
  }
}
