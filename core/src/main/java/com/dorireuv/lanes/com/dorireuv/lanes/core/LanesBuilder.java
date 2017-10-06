package com.dorireuv.lanes.com.dorireuv.lanes.core;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.LanesClient;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriberFactory;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriberGroup;
import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.Game;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.GameBuilder;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.Bank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.BankClientDecorator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.SimpleBank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.BoardClientDecorator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.BoardGenerator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.CheckerActionExecutor;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.move.MovesGenerator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.move.SingleMoveGenerator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.PlayerClientDecorator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import com.dorireuv.lanes.com.dorireuv.lanes.core.turn.TurnIterator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.SimpleRandomWrapper;
import java.util.LinkedList;
import java.util.List;

public class LanesBuilder {

  public LanesClient buildDefault(
      List<String> playersName, int numOfTurns, int numOfStars, long randomSeed)
      throws IllegalArgumentException {
    int numOfPlayers = playersName.size();
    validateNumOfPlayers(numOfPlayers);

    RandomWrapper randomWrapper = new SimpleRandomWrapper(randomSeed);
    int firstPlayerIndex = randomWrapper.nextInt(numOfPlayers);
    TurnIterator turnIterator = new TurnIterator(firstPlayerIndex, numOfPlayers, numOfTurns);
    ClientEventSubscriberGroup clientEventSubscriberGroup =
        ClientEventSubscriberFactory.getClientEventSubscriberGroup();
    List<Player> players = createPlayers(playersName, clientEventSubscriberGroup);
    Bank bank = new BankClientDecorator(new SimpleBank(), clientEventSubscriberGroup);
    Board board =
        new BoardClientDecorator(
            new BoardGenerator(new SimpleRandomWrapper(randomSeed))
                .generate(numOfStars, players.size()),
            clientEventSubscriberGroup);
    Game game = new GameBuilder().buildNewDefaultGame(players, board, bank);
    MovesGenerator movesGenerator =
        new MovesGenerator(new SingleMoveGenerator(randomWrapper), Config.getNumOfMoveOptions());
    CheckerActionExecutor checkerActionExecutor = new CheckerActionExecutor();
    Lanes lanes =
        new Lanes(
            game,
            randomWrapper,
            movesGenerator,
            clientEventSubscriberGroup,
            turnIterator,
            checkerActionExecutor);
    return new LanesClient(lanes);
  }

  private void validateNumOfPlayers(int numOfPlayers) throws IllegalArgumentException {
    if (numOfPlayers <= 1) {
      throw new IllegalArgumentException(
          String.format("invalid number of players %d <= 1", numOfPlayers));
    }
    int maxNumOfPlayers = Config.getMaxNumOfPlayers();
    if (numOfPlayers > maxNumOfPlayers) {
      throw new IllegalArgumentException(
          String.format("invalid number of players %d > %d", numOfPlayers, maxNumOfPlayers));
    }
  }

  private List<Player> createPlayers(
      List<String> playersName, ClientEventSubscriber clientEventSubscriber) {
    List<Player> players = new LinkedList<>();
    int index = 0;
    for (String playerName : playersName) {
      Player player =
          new PlayerClientDecorator(
              new SimplePlayer(index, playerName, Config.getPlayerGameStartCashMoney()),
              clientEventSubscriber);
      players.add(player);
      index++;
    }

    return players;
  }
}
