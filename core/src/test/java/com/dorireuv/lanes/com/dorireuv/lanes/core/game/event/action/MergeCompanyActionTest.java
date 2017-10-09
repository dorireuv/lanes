package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import static com.google.common.truth.Truth8.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.CompanyTopHolderChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import java.util.Arrays;
import java.util.List;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
final class MergeCompanyActionTest {

  @Mock private ClientEventSubscriber clientEventSubscriber;

  private final CompanyDefinition mergedIntoCompanyDefinition = CompanyDefinition.create('A', "A");
  private final CompanyDefinition mergedCompanyDefinition = CompanyDefinition.create('B', "B");

  private Player player1;
  private Player player2;

  private Board board;
  private MergeCompanyAction mergeCompanyAction;
  private Company mergedIntoCompany;
  private Company mergedCompany;
  private Position position;

  @BeforeEach
  void setUp() {
    player1 = new SimplePlayer(0, "A", 0);
    player2 = new SimplePlayer(1, "B", 0);
    List<Player> players = Arrays.asList(player1, player2);

    board = new SimpleBoard();

    position = Position.create(5, 5);

    mergedIntoCompany = new Company(mergedIntoCompanyDefinition);
    mergedCompany = new Company(mergedCompanyDefinition);

    mergeCompanyAction =
        new MergeCompanyAction(
            clientEventSubscriber, players, board, position, mergedIntoCompany, mergedCompany);
  }

  private void mergeCompanies() {
    mergeCompanyAction.doAction();
  }

  @Test
  void playersHolding() throws Exception {
    player1.setNumOfStocks(mergedIntoCompany, 100);
    player1.setNumOfStocks(mergedCompany, 200);

    mergeCompanies();

    assertEquals(player1.getNumOfStocks(mergedIntoCompany), 200);
    assertEquals(player1.getNumOfStocks(mergedCompany), 0);
  }

  @Test
  void playersCashMoney() throws Exception {
    mergedIntoCompany.setValue(1000);

    player1.setNumOfStocks(mergedCompany, 200);
    player2.setNumOfStocks(mergedCompany, 300);

    mergeCompanies();

    assertEquals(player1.getCashMoney(), 4000);
    assertEquals(player2.getCashMoney(), 6000);
  }

  @Test
  void companiesSize() throws Exception {
    mergedIntoCompany.setSize(10);
    mergedCompany.setSize(5);

    mergeCompanies();

    assertEquals(mergedIntoCompany.getSize(), 16);
    assertEquals(mergedCompany.getSize(), 0);
  }

  @Test
  void toolAfterMergeIsOfTheMergedIntoCompany() {
    mergeCompanies();
    Tool companyTool = board.getTool(position);
    assertThat(companyTool.getCompanyDefinition()).hasValue(mergedIntoCompanyDefinition);
  }

  @Test
  void mergedIntoCompanyValue() {
    mergedIntoCompany.setValue(1000);
    mergedCompany.setValue(1200);

    mergeCompanies();

    assertEquals(mergedIntoCompany.getValue(), 2200);
    assertEquals(mergedCompany.getValue(), 0);
  }

  @Test
  void board() {
    Position mergedCompanyPosition = position.move(0, +1);
    board.getTool(mergedCompanyPosition).setCompanyDefinition(mergedCompanyDefinition);

    mergeCompanies();

    Tool companyTool = board.getTool(mergedCompanyPosition);
    assertThat(companyTool.getCompanyDefinition()).hasValue(mergedIntoCompanyDefinition);
  }

  @Test
  void companyTopHolderChange() {
    mergedIntoCompany.setValue(1000);

    player1.setNumOfStocks(mergedIntoCompany, 100);
    player2.setNumOfStocks(mergedIntoCompany, 50);
    player1.setNumOfStocks(mergedCompany, 0);
    player2.setNumOfStocks(mergedCompany, 400);

    mergeCompanies();

    // TODO check MergeInfo
    ArgumentCaptor<CompanyTopHolderChangeEvent> companyTopHolderChangeEvent =
        ArgumentCaptor.forClass(CompanyTopHolderChangeEvent.class);
    verify(clientEventSubscriber, times(1))
        .onCompanyTopHolderChange(companyTopHolderChangeEvent.capture());
    assertEquals(companyTopHolderChangeEvent.getValue().getTopHolderIndex(), player2.getIndex());
  }
}
