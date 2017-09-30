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
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriberGroup;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.CompanyTopHolderChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.exception.IllegalMoveException;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.Game;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.GameBuilder;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.Bank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank.SimpleBank;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.CheckerActionExecutor;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker.BankCashMoneyInterestChecker;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker.BoardChecker;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker.BonusPaymentChecker;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker.DoublePaymentChecker;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker.FreezeChecker;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker.GalacticBombChecker;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker.GoldStarDisappearChecker;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker.PlayerCashMoneyInterestChecker;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker.TrapChecker;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.move.MovesGenerator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.move.SingleMoveGenerator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import com.dorireuv.lanes.com.dorireuv.lanes.core.turn.TurnIterator;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.SimpleRandomWrapper;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class LanesTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ClientEventSubscriberGroup clientEventSubscriberGroup;

  private Lanes lanes;

  private Player player1;
  private Player player2;
  private List<CompanyDefinition> companyDefinitions;
  private CheckerActionExecutor checkerActionExecutor;

  @Before
  public void setUp() throws Exception {
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
    TurnIterator turnIterator = spy(new TurnIterator(firstPlayerIndex, numOfPlayers, numOfTurns));
    when(turnIterator.shouldEndTurn()).thenAnswer(invocationOnMock -> false);

    checkerActionExecutor = spy(new CheckerActionExecutor());

    lanes =
        new Lanes(
            game,
            randomWrapper,
            movesGenerator,
            clientEventSubscriberGroup,
            turnIterator,
            checkerActionExecutor);
    companyDefinitions = lanes.getGame().getCompanyDefinitions();
  }

  @Test
  public void testDoMoveThrowsExceptionBeforePlayerEndedTurn() {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().get(0));
    assertThrows(IllegalMoveException.class, () -> lanes.doMove(lanes.getMoves().get(0)));
  }

  @Test
  public void testEndTurnThrowsExceptionBeforePlayerDoMove() {
    lanes.acceptBoard();
    lanes.startGame();
    assertThrows(IllegalMoveException.class, () -> lanes.endTurn());
  }

  @Test
  public void testOrder() {
    InOrder inOrder = inOrder(checkerActionExecutor);
    lanes.acceptBoard();
    lanes.startGame();
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any(GoldStarDisappearChecker.class));
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any(GalacticBombChecker.class));

    lanes.doMove(lanes.getMoves().get(0));
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any(BoardChecker.class));
    inOrder
        .verify(checkerActionExecutor, calls(1))
        .execute(any(PlayerCashMoneyInterestChecker.class));
    inOrder
        .verify(checkerActionExecutor, calls(1))
        .execute(any(BankCashMoneyInterestChecker.class));
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any(DoublePaymentChecker.class));
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any(TrapChecker.class));
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any(BonusPaymentChecker.class));
    inOrder.verify(checkerActionExecutor, calls(1)).execute(any(FreezeChecker.class));
    verifyNoMoreInteractions(checkerActionExecutor);
  }

  @Test
  public void testPhases() throws Exception {
    lanes.getTurnIterator().setNumOfTurns(1);

    assertEquals(lanes.getCurrentPhase(), Phase.GAME_NOT_STARTED);
    lanes.acceptBoard();
    assertEquals(lanes.getCurrentPhase(), Phase.BOARD_CHOSEN);
    lanes.startGame();
    assertEquals(lanes.getCurrentPhase(), Phase.TURN_MOVE);
    lanes.doMove(lanes.getMoves().get(0));
    assertEquals(lanes.getCurrentPhase(), Phase.TURN_TRADE);
    lanes.endTurn();
    assertEquals(lanes.getCurrentPhase(), Phase.GAME_ENDED);
  }

  @Test
  public void testBuy() {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().get(0));

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
  public void testBuyDoesNotDispatchCompanyTopHolderChangeEvent() {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().get(0));

    CompanyDefinition companyDefinitionA = companyDefinitions.get(0);
    Company companyA = lanes.getGame().getCompany(companyDefinitionA);
    companyA.setValue(1000);
    player1.setCashMoney(22500);
    player1.setNumOfStocks(companyA, 1);
    lanes.buy(companyDefinitionA, 22);
    verify(clientEventSubscriberGroup, never())
        .onCompanyTopHolderChange(any(CompanyTopHolderChangeEvent.class));
  }

  @Test
  public void testBuyThrowsExceptionWhenNotEnoughMoney() {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().get(0));

    CompanyDefinition companyDefinitionA = companyDefinitions.get(0);
    Company companyA = lanes.getGame().getCompany(companyDefinitionA);
    companyA.setValue(1000);
    player1.setCashMoney(22500);
    player1.setNumOfStocks(companyA, 100);

    assertThrows(IllegalMoveException.class, () -> lanes.buy(companyDefinitionA, 23));
  }

  @Test
  public void testSell() {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().get(0));

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
  public void testSellDoesNotDispatchCompanyTopHolderChangeEvent() {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().get(0));

    CompanyDefinition companyDefinitionA = companyDefinitions.get(0);
    Company companyA = lanes.getGame().getCompany(companyDefinitionA);
    companyA.setValue(1000);
    player1.setCashMoney(1000);
    player1.setNumOfStocks(companyA, 170);
    player2.setNumOfStocks(companyA, 100);

    lanes.sell(companyDefinitionA, 50);
    verify(clientEventSubscriberGroup, never())
        .onCompanyTopHolderChange(any(CompanyTopHolderChangeEvent.class));
  }

  @Test
  public void testSellThrowsExceptionWhenNotEnoughStocks() {
    lanes.acceptBoard();
    lanes.startGame();
    lanes.doMove(lanes.getMoves().get(0));

    CompanyDefinition companyDefinitionA = companyDefinitions.get(0);
    Company companyA = lanes.getGame().getCompany(companyDefinitionA);
    companyA.setValue(1000);
    player1.setCashMoney(1000);
    player1.setNumOfStocks(companyA, 100);

    assertThrows(IllegalMoveException.class, () -> lanes.sell(companyDefinitionA, 101));
  }
}
