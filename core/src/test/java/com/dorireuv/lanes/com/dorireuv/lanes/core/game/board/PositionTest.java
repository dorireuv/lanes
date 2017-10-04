package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class PositionTest {

  @Test
  public void move() throws Exception {
    Position position = Position.create(1, 2);
    Position anotherPosition = position.move(2, 3);
    assertEquals(anotherPosition.getRow(), 3);
    assertEquals(anotherPosition.getCol(), 5);
  }

  @Test
  public void equal() throws Exception {
    Position position1 = Position.create(1, 2);
    Position position2 = Position.create(1, 2);
    assertEquals(position1, position2);
  }

  @Test
  public void notEqual() throws Exception {
    Position position1 = Position.create(1, 2);
    Position position2 = Position.create(2, 1);
    assertNotEquals(position1, position2);
  }

  @Test
  public void hashCodeSameForSamePositions() throws Exception {
    Position position1 = Position.create(1, 2);
    Position position2 = Position.create(1, 2);
    assertEquals(position1.hashCode(), position2.hashCode());
  }

  @Test
  public void hashCodeDifferentForDifferentPositions() throws Exception {
    assertNotEquals(Position.create(1, 2).hashCode(), Position.create(2, 1).hashCode());
  }
}
