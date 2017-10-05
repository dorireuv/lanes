package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class RepeatedAssignerTest {

  @Mock private Assigner assigner;

  @Test
  void assign() throws Exception {
    int numOfAssignments = 3;
    RepeatedAssigner repeatedAssigner = new RepeatedAssigner(assigner, numOfAssignments);
    repeatedAssigner.assign();
    verify(assigner, times(3)).assign();
  }
}
