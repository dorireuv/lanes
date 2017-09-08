package com.dorireuv.lanes.com.dorireuv.lanes.core.data.company;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class CompanyDefinition implements Comparable<CompanyDefinition> {

  public static CompanyDefinition create(Character symbol, String name) {
    return new AutoValue_CompanyDefinition(symbol, name);
  }

  public abstract Character getSymbol();

  public abstract String getName();

  @Override
  public int compareTo(CompanyDefinition o) {
    return getSymbol().compareTo(o.getSymbol());
  }
}
