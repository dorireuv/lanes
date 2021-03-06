package com.dorireuv.lanes.com.dorireuv.lanes.core;

import static org.junit.Assert.assertEquals;
import static org.junit.gen5.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriberGroup;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.CompanyTopHolderChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.Game;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.GameBuilder;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.Bank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.SimpleBank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.BoardGenerator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.CheckerActionExecutor;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.move.MovesGenerator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.move.SingleMoveGenerator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import com.dorireuv.lanes.com.dorireuv.lanes.core.turn.TurnIterator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.SimpleRandomWrapper;
import java.util.Arrays;
import java.util.List;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
final class LanesTest {

  @Mock private ClientEventSubscriberGroup clientEventSubscriberGroup;

  private Lanes lanes;

  private Player player1;
  private Player player2;
  private List<CompanyDefinition> companyDefinitions;
  private CheckerActionExecutor checkerActionExecutor;

  @BeforeEach
  void setUp() throws Exception {
    player1 = new SimplePlayer(0, "P1", 0);
    player2 = new SimplePlayer(1, "P2", 0);
    List<Player> players = Arrays.asList(player1, player2);
    long randomSeed = 100;
    Bank bank = new SimpleBank();
    Board board = new SimpleBoard();
    Game game = new GameBuilder().buildNewDefaultGame(players, board, bank);

    RandomWrapper randomWrapper = new SimpleRandomWrapper(randomSeed);

    MovesGenerator movesGenerator =
        new MovesGenerator(new SingleMoveGenerator(randomWrapper), Config.getNumOfMoveOptions());

    int firstPlayerIndex = 0;
    int numOfPlayers = players.size();
    int numOfTurns = 100;
    TurnIterator turnIterator =
        TurnIterator.newBuilder()
            .numOfPlayers(numOfPlayers)
            .firstPlayerIndex(firstPlayerIndex)
            .numOfTurns(numOfTurns)
            .build();

    checkerActionExecutor = spy(new CheckerActionExecutor());

    lanes =
        new Lanes(
            game,
            randomWrapper,
            new BoardGenerator(randomWrapper, numOfPlayers, 100),
            movesGenerator,
            clientEventSubscriberGroup,
            turnIterator,
            checkerActionExecutor);
    companyDefinitions = lanes.getGame().getCompanyDefinitions();
  }

  @Test
  void doMoveThrowsExceptionBeforePlayerEndedTurn() throws Exception {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().asList().get(0));
    assertThrows(IllegalMoveException.class, () -> lanes.doMove(lanes.getMoves().asList().get(0)));
  }

  @Test
  void endTurnThrowsExceptionBeforePlayerDoMove() throws Exception {
    lanes.acceptBoard();
    lanes.startGame();
    assertThrows(IllegalMoveException.class, () -> lanes.endTurn());
  }

  @Test
  void order() throws Exception {
    InOrder inOrder = inOrder(checkerActionExecutor);
    lanes.acceptBoard();
    lanes.startGame();
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any());
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any());

    lanes.doMove(lanes.getMoves().asList().get(0));
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any());
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any());
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any());
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any());
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any());
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any());
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any());
    verifyNoMoreInteractions(checkerActionExecutor);
  }

  @Test
  void phases() throws Exception {
    lanes.getTurnIterator().setNumOfTurns(1);

    assertEquals(lanes.getCurrentPhase(), Phase.GAME_NOT_STARTED);
    lanes.acceptBoard();
    assertEquals(lanes.getCurrentPhase(), Phase.BOARD_CHOSEN);
    lanes.startGame();
    assertEquals(lanes.getCurrentPhase(), Phase.TURN_MOVE);
    lanes.doMove(lanes.getMoves().asList().get(0));
    assertEquals(lanes.getCurrentPhase(), Phase.TURN_TRADE);
    lanes.endTurn();
    assertEquals(lanes.getCurrentPhase(), Phase.GAME_ENDED);
  }

  @Test
  void buy() throws Exception {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().asList().get(0));

    CompanyDefinition companyDefinitionA = companyDefinitions.get(0);
    Company companyA = lanes.getGame().getCompany(companyDefinitionA);
    companyA.setValue(1000);
    player1.setCashMoney(22500);

    lanes.buy(companyDefinitionA, 22);

    assertEquals(player1.getCashMoney(), 500);
    assertEquals(player1.getNumOfStocks(companyA), 22);

    ArgumentCaptor<CompanyTopHolderChangeEvent> companyTopHolderChangeEvent =
        ArgumentCaptor.forClass(CompanyTopHolderChangeEvent.class);
    verify(clientEventSubscriberGroup, times(1))
        .onCompanyTopHolderChange(companyTopHolderChangeEvent.capture());
    assertEquals(companyTopHolderChangeEvent.getValue().getTopHolderIndex(), player1.getIndex());
  }

  @Test
  void buyDoesNotDispatchCompanyTopHolderChangeEvent() throws Exception {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().asList().get(0));

    CompanyDefinition companyDefinitionA = companyDefinitions.get(0);
    Company companyA = lanes.getGame().getCompany(companyDefinitionA);
    companyA.setValue(1000);
    player1.setCashMoney(22500);
    player1.setNumOfStocks(companyA, 1);
    lanes.buy(companyDefinitionA, 22);
    verify(clientEventSubscriberGroup, never()).onCompanyTopHolderChange(any());
  }

  @Test
  void buyThrowsExceptionWhenNotEnoughMoney() throws Exception {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().asList().get(0));

    CompanyDefinition companyDefinitionA = companyDefinitions.get(0);
    Company companyA = lanes.getGame().getCompany(companyDefinitionA);
    companyA.setValue(1000);
    player1.setCashMoney(22500);
    player1.setNumOfStocks(companyA, 100);

    assertThrows(IllegalMoveException.class, () -> lanes.buy(companyDefinitionA, 23));
  }

  @Test
  void sell() throws Exception {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().asList().get(0));

    CompanyDefinition companyDefinitionA = companyDefinitions.get(0);
    Company companyA = lanes.getGame().getCompany(companyDefinitionA);
    companyA.setValue(1000);
    player1.setCashMoney(1000);
    player1.setNumOfStocks(companyA, 120);
    player2.setNumOfStocks(companyA, 100);

    lanes.sell(companyDefinitionA, 50);

    assertEquals(player1.getCashMoney(), 48500);
    assertEquals(player1.getNumOfStocks(companyA), 70);

    ArgumentCaptor<CompanyTopHolderChangeEvent> companyTopHolderChangeEvent =
        ArgumentCaptor.forClass(CompanyTopHolderChangeEvent.class);
    verify(clientEventSubscriberGroup, times(1))
        .onCompanyTopHolderChange(companyTopHolderChangeEvent.capture());
    assertEquals(companyTopHolderChangeEvent.getValue().getTopHolderIndex(), player2.getIndex());
  }

  @Test
  void sellDoesNotDispatchCompanyTopHolderChangeEvent() throws Exception {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().asList().get(0));

    CompanyDefinition companyDefinitionA = companyDefinitions.get(0);
    Company companyA = lanes.getGame().getCompany(companyDefinitionA);
    companyA.setValue(1000);
    player1.setCashMoney(1000);
    player1.setNumOfStocks(companyA, 170);
    player2.setNumOfStocks(companyA, 100);

    lanes.sell(companyDefinitionA, 50);
    verify(clientEventSubscriberGroup, never()).onCompanyTopHolderChange(any());
  }

  @Test
  void sellThrowsExceptionWhenNotEnoughStocks() throws Exception {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().asList().get(0));

    CompanyDefinition companyDefinitionA = companyDefinitions.get(0);
    Company companyA = lanes.getGame().getCompany(companyDefinitionA);
    companyA.setValue(1000);
    player1.setCashMoney(1000);
    player1.setNumOfStocks(companyA, 100);

    assertThrows(IllegalMoveException.class, () -> lanes.sell(companyDefinitionA, 101));
  }
}
