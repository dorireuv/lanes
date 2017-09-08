package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PositionTest extends TestBase {

  @Test
  public void testMove() throws Exception {
    Position position = Position.create(1, 2);
    Position anotherPosition = position.move(2, 3);
    assertEquals(anotherPosition.getRow(), 3);
    assertEquals(anotherPosition.getCol(), 5);
  }

  @Test
  public void testEqual() throws Exception {
    Position position1 = Position.create(1, 2);
    Position position2 = Position.create(1, 2);
    assertEquals(position1, position2);
  }

  @Test
  public void testNotEqual() throws Exception {
    Position position1 = Position.create(1, 2);
    Position position2 = Position.create(2, 1);
    assertNotEquals(position1, position2);
  }

  @Test
  public void testHashCodeSameForSamePositions() throws Exception {
    Position position1 = Position.create(1, 2);
    Position position2 = Position.create(1, 2);
    assertEquals(position1.hashCode(), position2.hashCode());
  }

  @Test
  public void testHashCodeDifferentForDifferentPositions() throws Exception {
    assertNotEquals(Position.create(1, 2).hashCode(), Position.create(2, 1).hashCode());
  }
}
