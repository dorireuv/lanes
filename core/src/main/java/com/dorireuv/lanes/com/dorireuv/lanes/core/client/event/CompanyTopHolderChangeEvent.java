package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

@SuppressWarnings("UnusedDeclaration")
public class CompanyTopHolderChangeEvent implements Event {

  private final int topHolderIndex;

  public CompanyTopHolderChangeEvent(int topHolderIndex) {
    this.topHolderIndex = topHolderIndex;
  }

  public int getTopHolderIndex() {
    return topHolderIndex;
  }
}
