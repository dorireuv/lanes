package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import java.util.Optional;

public class Tool {

  public static Tool newEmptyTool() {
    Tool tool = new Tool();
    tool.setToolType(ToolType.EMPTY);
    return tool;
  }

  public static Tool newHitTool() {
    Tool tool = newEmptyTool();
    tool.setToolType(ToolType.HIT);
    return tool;
  }

  public static Tool newStarTool() {
    Tool tool = newEmptyTool();
    tool.setToolType(ToolType.STAR);
    return tool;
  }

  public static Tool newGoldStarTool() {
    Tool tool = newEmptyTool();
    tool.setToolType(ToolType.GOLD_STAR);
    return tool;
  }

  public static Tool newCompanyTool(CompanyDefinition companyDefinition) {
    Tool tool = newEmptyTool();
    tool.setCompanyDefinition(companyDefinition);
    return tool;
  }

  private ToolType toolType;
  private boolean isFreeze;
  private boolean isTrap;
  private boolean isDoublePayment;
  private Optional<CompanyDefinition> companyDefinition;

  private Tool() {
    this.setToolType(ToolType.EMPTY);
    this.clearCompanyDefinition();
    this.setIsFreeze(false);
    this.setIsTrap(false);
    this.setIsDoublePayment(false);
  }

  public ToolType getToolType() {
    return toolType;
  }

  public boolean isEmpty() {
    return getToolType().isEmpty();
  }

  public boolean isStar() {
    return getToolType().isStar();
  }

  public void setToolType(ToolType toolType) {
    this.toolType = toolType;
  }

  public Optional<CompanyDefinition> getCompanyDefinition() {
    return companyDefinition;
  }

  public void setCompanyDefinition(CompanyDefinition companyDefinition) {
    this.toolType = ToolType.COMPANY;
    this.companyDefinition = Optional.of(companyDefinition);
  }

  private void clearCompanyDefinition() {
    this.companyDefinition = Optional.empty();
  }

  public boolean isFreeze() {
    return isFreeze;
  }

  public void setIsFreeze(boolean isFreeze) {
    this.isFreeze = isFreeze;
  }

  public boolean isTrap() {
    return isTrap;
  }

  public void setIsTrap(boolean isTrap) {
    this.isTrap = isTrap;
  }

  public boolean isDoublePayment() {
    return isDoublePayment;
  }

  public void setIsDoublePayment(boolean isDoublePayment) {
    this.isDoublePayment = isDoublePayment;
  }
}
