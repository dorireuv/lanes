package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class RepeatedAssignerTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private Assigner assigner;

  @Test
  public void testAssign() throws Exception {
    int numOfAssignments = 3;
    RepeatedAssigner repeatedAssigner = new RepeatedAssigner(assigner, numOfAssignments);
    repeatedAssigner.assign();
    verify(assigner, times(3)).assign();
  }
}
