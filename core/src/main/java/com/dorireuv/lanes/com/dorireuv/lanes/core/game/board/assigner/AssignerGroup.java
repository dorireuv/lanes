package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

public class AssignerGroup implements Assigner {

  private final Assigner[] assigners;

  public AssignerGroup(Assigner... assigners) {
    this.assigners = assigners;
  }

  public void assign() {
    for (Assigner assigner : assigners) {
      assigner.assign();
    }
  }
}
