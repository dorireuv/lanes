package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

public class RepeatedAssigner implements Assigner {

  private final Assigner assigner;
  private final int numOfAssignments;

  public RepeatedAssigner(Assigner assigner, int numOfAssignments) {
    this.assigner = assigner;
    this.numOfAssignments = numOfAssignments;
  }

  @Override
  public void assign() {
    for (int curNumOfAssignments = 0;
         curNumOfAssignments < numOfAssignments;
         curNumOfAssignments++) {
      assigner.assign();
    }
  }
}
