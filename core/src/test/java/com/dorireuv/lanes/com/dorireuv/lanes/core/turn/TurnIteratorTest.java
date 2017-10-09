package com.dorireuv.lanes.com.dorireuv.lanes.core.turn;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.dorireuv.lanes.com.dorireuv.lanes.core.turn.TurnIterator.TurnIteratorBuilder;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

final class TurnIteratorTest {

  @Test
  void build_numOfPlayersLessThan2_throwsIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> newValidTurnIteratorBuilder().numOfPlayers(1).build());
  }

  @Test
  void build_firstPlayerIndexIsLessThan0_throwsIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> newValidTurnIteratorBuilder().firstPlayerIndex(-1).build());
  }

  @Test
  void build_firstPlayerIndexIsEqualToOrGreaterThanNumOfPlayers_throwsIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> newValidTurnIteratorBuilder().numOfPlayers(2).firstPlayerIndex(2).build());
  }

  @Test
  void build_numOfTurnsIsNegative_throwsIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class, () -> newValidTurnIteratorBuilder().numOfTurns(-1).build());
  }

  @Test
  void build_numOfTurnsModuloNumOfPlayersIsNotZero_throwsIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> newValidTurnIteratorBuilder().numOfPlayers(3).numOfTurns(7).build());
  }

  @Test
  void peek_firstTurn_returnsFirstTurn() {
    TurnIterator turnIterator =
        TurnIterator.newBuilder().numOfPlayers(3).firstPlayerIndex(2).numOfTurns(99).build();

    Turn current = turnIterator.peek();

    assertThat(current).isEqualTo(Turn.builder().setPlayerIndex(2).setNumber(1).build());
  }

  @Test
  void peek_secondTurn_returnsSecondTurn() {
    TurnIterator turnIterator =
        TurnIterator.newBuilder().numOfPlayers(3).firstPlayerIndex(2).numOfTurns(99).build();

    turnIterator.next();
    Turn current = turnIterator.peek();

    assertThat(current).isEqualTo(Turn.builder().setPlayerIndex(0).setNumber(2).build());
  }

  @Test
  void next_lastTurn_throwsNoSuchElementException() {
    TurnIterator turnIterator =
        TurnIterator.newBuilder().numOfPlayers(2).firstPlayerIndex(1).numOfTurns(2).build();
    turnIterator.next();

    assertThrows(NoSuchElementException.class, turnIterator::next);
  }

  @Test
  void hasNext_notLastTurn_returnsTrue() {
    TurnIterator turnIterator =
        TurnIterator.newBuilder().numOfPlayers(2).firstPlayerIndex(1).numOfTurns(2).build();

    boolean hasNext = turnIterator.hasNext();

    assertThat(hasNext).isTrue();
  }

  @Test
  void hasNext_lastTurn_returnsFalse() {
    TurnIterator turnIterator =
        TurnIterator.newBuilder().numOfPlayers(2).firstPlayerIndex(1).numOfTurns(2).build();

    turnIterator.next();
    boolean hasNext = turnIterator.hasNext();

    assertThat(hasNext).isFalse();
  }

  private static TurnIteratorBuilder newValidTurnIteratorBuilder() {
    return TurnIterator.newBuilder().numOfPlayers(2).firstPlayerIndex(1).numOfTurns(4);
  }
}
