package com.dorireuv.lanes.com.dorireuv.lanes.core.turn;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.PeekingIterator;
import com.google.common.collect.UnmodifiableIterator;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

/** Iterates over the turns in a game. */
public final class TurnIterator extends UnmodifiableIterator<Turn>
    implements PeekingIterator<Turn> {

  public static TurnIteratorBuilder newBuilder() {
    return new TurnIteratorBuilder();
  }

  private final int numOfPlayers;

  private int numOfTurns;
  private Turn currentTurn;
  private boolean shouldEndTurn;

  private TurnIterator(int numOfPlayers, int firstPlayerIndex, int numOfTurns) {
    checkArgument(numOfPlayers >= 2);
    checkArgument(firstPlayerIndex >= 0 && firstPlayerIndex < numOfPlayers);
    checkArgument(numOfTurns > 0);
    checkArgument(numOfTurns % numOfPlayers == 0);

    this.numOfPlayers = numOfPlayers;
    this.numOfTurns = numOfTurns;

    currentTurn = Turn.builder().setNumber(1).setPlayerIndex(firstPlayerIndex).build();
    shouldEndTurn = false;
  }

  @Override
  public boolean hasNext() {
    return currentTurn.getNumber() < numOfTurns;
  }

  @Override
  public Turn next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    shouldEndTurn = false;
    currentTurn =
        Turn.builder()
            .setPlayerIndex((currentTurn.getPlayerIndex() + 1) % numOfPlayers)
            .setNumber(currentTurn.getNumber() + 1)
            .build();
    return currentTurn;
  }

  @Override
  public Turn peek() {
    return currentTurn;
  }

  public void setNumOfTurns(int numOfTurns) {
    this.numOfTurns = numOfTurns;
  }

  public int getNumOfTurns() {
    return numOfTurns;
  }

  // TODO(dorireuv) think how to remove this.
  public void setShouldEndTurn() {
    this.shouldEndTurn = true;
  }

  public boolean shouldEndTurn() {
    return shouldEndTurn;
  }

  /** Builder for {@link TurnIterator}. */
  public static class TurnIteratorBuilder {

    @Nullable private Integer numOfPlayers;
    @Nullable private Integer firstPlayerIndex;
    @Nullable private Integer numOfTurns;

    private TurnIteratorBuilder() {}

    public TurnIteratorBuilder numOfPlayers(int numOfPlayers) {
      this.numOfPlayers = numOfPlayers;
      return this;
    }

    public TurnIteratorBuilder firstPlayerIndex(int firstPlayerIndex) {
      this.firstPlayerIndex = firstPlayerIndex;
      return this;
    }

    public TurnIteratorBuilder numOfTurns(int numOfTurns) {
      this.numOfTurns = numOfTurns;
      return this;
    }

    public TurnIterator build() {
      return new TurnIterator(
          checkNotNull(numOfPlayers), checkNotNull(firstPlayerIndex), checkNotNull(numOfTurns));
    }
  }
}
