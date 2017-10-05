package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

class PositionTest {

  @Test
  void move() throws Exception {
    Position position = Position.create(1, 2);
    Position anotherPosition = position.move(2, 3);
    assertEquals(anotherPosition.getRow(), 3);
    assertEquals(anotherPosition.getCol(), 5);
  }

  @Test
  void equal() throws Exception {
    Position position1 = Position.create(1, 2);
    Position position2 = Position.create(1, 2);
    assertEquals(position1, position2);
  }

  @Test
  void notEqual() throws Exception {
    Position position1 = Position.create(1, 2);
    Position position2 = Position.create(2, 1);
    assertNotEquals(position1, position2);
  }

  @Test
  void hashCodeSameForSamePositions() throws Exception {
    Position position1 = Position.create(1, 2);
    Position position2 = Position.create(1, 2);
    assertEquals(position1.hashCode(), position2.hashCode());
  }

  @Test
  void hashCodeDifferentForDifferentPositions() throws Exception {
    assertNotEquals(Position.create(1, 2).hashCode(), Position.create(2, 1).hashCode());
  }
}
