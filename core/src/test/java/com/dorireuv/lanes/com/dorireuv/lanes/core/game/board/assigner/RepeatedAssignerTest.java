package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RepeatedAssignerTest extends TestBase {

  @Mock
  private Assigner assigner;

  @Test
  public void testAssign() throws Exception {
    int numOfAssignments = 3;
    RepeatedAssigner repeatedAssigner = new RepeatedAssigner(assigner, numOfAssignments);
    repeatedAssigner.assign();
    verify(assigner, times(3)).assign();
  }
}
