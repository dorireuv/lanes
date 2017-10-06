package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.google.auto.value.AutoValue;
import java.util.Optional;

@AutoValue
public abstract class ImmutableTool {

  public static final ImmutableTool EMPTY = builder().setToolType(ToolType.EMPTY).build();

  public abstract ToolType getToolType();

  public final boolean isEmpty() {
    return getToolType().isEmpty();
  }

  public final boolean isStar() {
    return getToolType().isStar();
  }

  public abstract Optional<CompanyDefinition> getCompanyDefinition();

  public static Builder builder() {
    return new AutoValue_ImmutableTool.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder setToolType(ToolType toolType);

    public abstract Builder setCompanyDefinition(Optional<CompanyDefinition> companyDefinition);

    public abstract ImmutableTool build();
  }
}
