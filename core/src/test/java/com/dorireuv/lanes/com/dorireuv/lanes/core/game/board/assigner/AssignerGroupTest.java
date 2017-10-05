package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class AssignerGroupTest {

  @Mock private Assigner assigner1;
  @Mock private Assigner assigner2;

  @Test
  void assign() throws Exception {
    AssignerGroup assignerGroup = new AssignerGroup(assigner1, assigner2);
    assignerGroup.assign();
    verify(assigner1, times(1)).assign();
    verify(assigner2, times(1)).assign();
  }
}
