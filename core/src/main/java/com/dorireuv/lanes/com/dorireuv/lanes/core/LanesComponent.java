package com.dorireuv.lanes.com.dorireuv.lanes.core;

import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.NumOfPlayers;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.NumOfStars;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.NumOfTurns;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.PlayerNames;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.RandomSeed;
import com.google.common.collect.ImmutableList;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;

@Component(modules = {LanesModule.class})
@Singleton
interface LanesComponent {
  Lanes lanes();

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder randomSeed(@RandomSeed long randomSeed);

    @BindsInstance
    Builder numOfPlayers(@NumOfPlayers int numOfPlayers);

    @BindsInstance
    Builder numOfStars(@NumOfStars int numOfStars);

    @BindsInstance
    Builder numOfTurns(@NumOfTurns int numOfTurns);

    @BindsInstance
    Builder playerNames(@PlayerNames ImmutableList<String> playerNames);

    LanesComponent build();
  }
}
