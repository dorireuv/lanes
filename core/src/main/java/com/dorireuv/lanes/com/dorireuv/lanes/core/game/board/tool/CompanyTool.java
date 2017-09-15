package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.data.CompanyData;

public class CompanyTool extends NonEmptyTool {

  private final CompanyData data;

  public CompanyTool(CompanyDefinition companyDefinition) {
    super();
    this.data = new CompanyData(companyDefinition);
  }

  public CompanyTool(Tool tool, CompanyDefinition companyDefinition) {
    super(tool);
    this.data = new CompanyData(companyDefinition);
  }

  @Override
  public CompanyData getData() {
    return data;
  }
}
