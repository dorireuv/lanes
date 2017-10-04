package com.dorireuv.lanes.com.dorireuv.lanes.core.turn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TurnIteratorTest {

  private TurnIterator turnIterator;

  @Before
  public void setUp() {
    int firstPlayerIndex = 1;
    int numOfPlayers = 3;
    int numOfTurns = 100;
    turnIterator = new TurnIterator(firstPlayerIndex, numOfPlayers, numOfTurns);
  }

  @Test
  public void getCurrentTurnOnBeginningReturnsOne() {
    assertEquals(turnIterator.getCurrentTurn(), 1);
  }

  @Test
  public void hasNextWhenNoTurnsLeftReturnsFalse() {
    assertEquals(turnIterator.getNumOfTurns(), 100);
    turnIterator.setNumOfTurns(1);
    assertEquals(turnIterator.getNumOfTurns(), 1);
    assertFalse(turnIterator.hasNext());
  }

  @Test
  public void getCurrentPlayerIndexOnBeginningReturnsFirstPlayerIndex() {
    assertEquals(turnIterator.getCurrentPlayerIndex(), turnIterator.getFirstPlayerIndex());
  }

  @Test
  public void next() {
    turnIterator.next();
    assertEquals(turnIterator.getCurrentTurn(), 2);
    assertEquals(turnIterator.getCurrentPlayerIndex(), 2);

    turnIterator.next();
    assertEquals(turnIterator.getCurrentTurn(), 3);
    assertEquals(turnIterator.getCurrentPlayerIndex(), 0);
  }

  @Test
  public void setShouldEndTurnIsFalseAfterNext() {
    assertFalse(turnIterator.shouldEndTurn());
    turnIterator.setShouldEndTurn();
    assertTrue(turnIterator.shouldEndTurn());
    turnIterator.next();
    assertFalse(turnIterator.shouldEndTurn());
  }
}
