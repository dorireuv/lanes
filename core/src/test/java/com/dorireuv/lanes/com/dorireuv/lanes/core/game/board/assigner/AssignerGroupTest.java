package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AssignerGroupTest extends TestBase {

  @Mock
  private Assigner assigner1;
  @Mock
  private Assigner assigner2;

  @Test
  public void testAssign() throws Exception {
    AssignerGroup assignerGroup = new AssignerGroup(assigner1, assigner2);
    assignerGroup.assign();
    verify(assigner1, times(1)).assign();
    verify(assigner2, times(1)).assign();
  }
}
