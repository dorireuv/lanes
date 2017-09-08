package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

public abstract class ToolBase implements Tool {

  private boolean isFreeze;
  private boolean isTrap;
  private boolean isDoublePayment;

  ToolBase(Tool tool) {
    this(tool.isFreeze(), tool.isTrap(), tool.isDoublePayment());
  }

  private ToolBase(boolean isFreeze, boolean isTrap, boolean isDoublePayment) {
    this.setIsFreeze(isFreeze);
    this.setIsTrap(isTrap);
    this.setIsDoublePayment(isDoublePayment);
  }

  ToolBase() {
    this.setIsFreeze(false);
    this.setIsTrap(false);
    this.setIsDoublePayment(false);
  }

  @Override
  public boolean isFreeze() {
    return this.isFreeze;
  }

  @Override
  public void setIsFreeze(boolean isFreeze) {
    this.isFreeze = isFreeze;
  }

  @Override
  public boolean isTrap() {
    return this.isTrap;
  }

  @Override
  public void setIsTrap(boolean isTrap) {
    this.isTrap = isTrap;
  }

  @Override
  public boolean isDoublePayment() {
    return isDoublePayment;
  }

  @Override
  public void setIsDoublePayment(boolean isDoublePayment) {
    this.isDoublePayment = isDoublePayment;
  }

  public boolean equals(Object other) {
    Tool otherTool = (Tool) other;
    return (isFreeze() == otherTool.isFreeze()
        && isTrap() == otherTool.isTrap()
        && isDoublePayment() == otherTool.isDoublePayment()
        && getClass().equals(otherTool.getClass())
        && getData().equals(otherTool.getData()));
  }
}
