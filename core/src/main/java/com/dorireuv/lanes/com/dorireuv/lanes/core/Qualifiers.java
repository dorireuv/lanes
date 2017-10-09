package com.dorireuv.lanes.com.dorireuv.lanes.core;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import javax.inject.Qualifier;

public final class Qualifiers {

  private Qualifiers() {}

  @Qualifier
  @Retention(RUNTIME)
  @interface RandomSeed {}

  @Qualifier
  @Retention(RUNTIME)
  public @interface NumOfPlayers {}

  @Qualifier
  @Retention(RUNTIME)
  public @interface NumOfStars {}

  @Qualifier
  @Retention(RUNTIME)
  @interface FirstPlayerIndex {}

  @Qualifier
  @Retention(RUNTIME)
  @interface NumOfTurns {}

  @Qualifier
  @Retention(RUNTIME)
  @interface PlayerNames {}

  @Qualifier
  @Retention(RUNTIME)
  public @interface Players {}

  @Qualifier
  @Retention(RUNTIME)
  public @interface CompanyDefinitions {}

  @Qualifier
  @Retention(RUNTIME)
  public @interface GalacticBombEventDefinitions {}

  @Qualifier
  @Retention(RUNTIME)
  public @interface NumOfMoveOptions {}
}
