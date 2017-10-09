package com.dorireuv.lanes.com.dorireuv.lanes.core;

import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.CompanyDefinitions;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.FirstPlayerIndex;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.GalacticBombEventDefinitions;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.NumOfMoveOptions;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.NumOfPlayers;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.NumOfTurns;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.PlayerNames;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.Players;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.RandomSeed;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriberFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriberGroup;
import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.JsonStringCompanyLoader;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.GalacticBombEventDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.JsonStringEventLoader;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.Bank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.BankClientDecorator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.SimpleBank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.BoardClientDecorator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.BoardGenerator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.PlayerClientDecorator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import com.dorireuv.lanes.com.dorireuv.lanes.core.turn.TurnIterator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.SimpleRandomWrapper;
import com.google.common.collect.ImmutableList;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
final class LanesModule {

  @Provides
  @Singleton
  static RandomWrapper provideRandomWrapper(@RandomSeed long seed) {
    return new SimpleRandomWrapper(seed);
  }

  @Provides
  @Singleton
  static ClientEventSubscriberGroup provideClientEventSubscriberGroup() {
    return ClientEventSubscriberFactory.getClientEventSubscriberGroup();
  }

  @Provides
  static TurnIterator provideTurnIterator(
      @NumOfPlayers int numOfPlayers,
      @FirstPlayerIndex int firstPlayerIndex,
      @NumOfTurns int numOfTurns) {
    return TurnIterator.newBuilder()
        .numOfPlayers(numOfPlayers)
        .firstPlayerIndex(firstPlayerIndex)
        .numOfTurns(numOfTurns)
        .build();
  }

  @Provides
  @Players
  static ImmutableList<Player> providePlayers(
      @PlayerNames ImmutableList<String> playerNames,
      ClientEventSubscriberGroup clientEventSubscriber) {
    ImmutableList.Builder<Player> players = ImmutableList.builder();
    int index = 0;
    for (String playerName : playerNames) {
      Player player =
          new PlayerClientDecorator(
              new SimplePlayer(index, playerName, Config.getPlayerGameStartCashMoney()),
              clientEventSubscriber);
      players.add(player);
      index++;
    }

    return players.build();
  }

  @Provides
  @Singleton
  @CompanyDefinitions
  static ImmutableList<CompanyDefinition> provideCompaniesDefinitions() {
    return JsonStringCompanyLoader.loadPredefined();
  }

  @Provides
  @Singleton
  @GalacticBombEventDefinitions
  static ImmutableList<GalacticBombEventDefinition> provideGalacticBombEventDefinitions() {
    return JsonStringEventLoader.loadPredefined();
  }

  @Provides
  @Singleton
  static Bank provideBank(ClientEventSubscriberGroup clientEventSubscriberGroup) {
    return new BankClientDecorator(
        new SimpleBank(Config.getBankGameStartCashMoney()), clientEventSubscriberGroup);
  }

  @Provides
  @Singleton
  static Board provideBoard(
      BoardGenerator boardGenerator, ClientEventSubscriberGroup clientEventSubscriberGroup) {
    return new BoardClientDecorator(boardGenerator.generate(), clientEventSubscriberGroup);
  }

  @Provides
  @NumOfMoveOptions
  static int provideNumOfMoves() {
    return Config.getNumOfMoveOptions();
  }

  @Provides
  @FirstPlayerIndex
  static int provideFirstPlayerIndex(RandomWrapper randomWrapper, @NumOfPlayers int numOfPlayers) {
    return randomWrapper.nextInt(numOfPlayers);
  }
}
