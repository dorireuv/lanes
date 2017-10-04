package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

public class BonusPaymentEvent implements Event {

  private final int amount;

  public BonusPaymentEvent(int amount) {
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }
}
