package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import com.google.common.collect.ImmutableList;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class CompanyUpdateActionTest {

  @Mock private ClientEventSubscriber clientEventSubscriber;

  private final CompanyDefinition companyDefinition = CompanyDefinition.create('A', "A");
  private final int size = 10;
  private final int value = 100;
  private Company company;
  private Player player1;
  private Player player2;
  private ImmutableList<Player> players;

  @BeforeEach
  void setUp() {
    company = new Company(companyDefinition, size, value);
    player1 = new SimplePlayer(0, "A", 0);
    player2 = new SimplePlayer(1, "B", 0);
    players = ImmutableList.of(player1, player2);
  }

  @Test
  void doAction1() {
    // ...
    // .A.
    // ...
    final Position position = Position.create(5, 5);
    Board board =
        SimpleBoard.newSimpleBoardBuilder()
            .put(position, Tool.newCompanyTool(companyDefinition))
            .build();

    int sizeDiff = 0;
    int valueDiff = 0;

    CompanyUpdateAction companyUpdateAction =
        new CompanyUpdateAction(clientEventSubscriber, board, position, company, players);
    companyUpdateAction.doAction();
    assertThat(company.getSize()).isEqualTo(size + sizeDiff);
    assertThat(company.getValue()).isEqualTo(value + valueDiff);

    assertThat(board.getTool(position.move(-1, -1)).getToolType()).isEqualTo(ToolType.EMPTY);
    assertThat(board.getTool(position.move(-1, 0)).getToolType()).isEqualTo(ToolType.EMPTY);
    assertThat(board.getTool(position.move(-1, +1)).getToolType()).isEqualTo(ToolType.EMPTY);
    assertThat(board.getTool(position.move(0, -1)).getToolType()).isEqualTo(ToolType.EMPTY);
    assertThat(board.getTool(position.move(0, 0)).getCompanyDefinition())
        .hasValue(companyDefinition);
    assertThat(board.getTool(position.move(0, +1)).getToolType()).isEqualTo(ToolType.EMPTY);
    assertThat(board.getTool(position.move(+1, -1)).getToolType()).isEqualTo(ToolType.EMPTY);
    assertThat(board.getTool(position.move(+1, 0)).getToolType()).isEqualTo(ToolType.EMPTY);
    assertThat(board.getTool(position.move(+1, -1)).getToolType()).isEqualTo(ToolType.EMPTY);
  }

  @Test
  void doAction2() {
    // +++    AAA
    // +A+ => AAA
    // +++    AAA
    final Position position = Position.create(5, 5);
    Board board =
        SimpleBoard.newSimpleBoardBuilder()
            .put(position, Tool.newCompanyTool(companyDefinition))
            .put(position.move(-1, -1), Tool.newHitTool())
            .put(position.move(-1, 0), Tool.newHitTool())
            .put(position.move(-1, +1), Tool.newHitTool())
            .put(position.move(0, -1), Tool.newHitTool())
            .put(position.move(0, +1), Tool.newHitTool())
            .put(position.move(+1, -1), Tool.newHitTool())
            .put(position.move(+1, 0), Tool.newHitTool())
            .put(position.move(+1, +1), Tool.newHitTool())
            .build();

    int sizeDiff = 8;
    int valueDiff = 800;

    CompanyUpdateAction companyUpdateAction =
        new CompanyUpdateAction(clientEventSubscriber, board, position, company, players);
    companyUpdateAction.doAction();
    assertThat(company.getSize()).isEqualTo(size + sizeDiff);
    assertThat(company.getValue()).isEqualTo(value + valueDiff);

    assertThat(board.getTool(position.move(-1, -1)).getCompanyDefinition())
        .hasValue(companyDefinition);
    assertThat(board.getTool(position.move(-1, 0)).getCompanyDefinition())
        .hasValue(companyDefinition);
    assertThat(board.getTool(position.move(-1, +1)).getCompanyDefinition())
        .hasValue(companyDefinition);
    assertThat(board.getTool(position.move(0, -1)).getCompanyDefinition())
        .hasValue(companyDefinition);
    assertThat(board.getTool(position.move(0, 0)).getCompanyDefinition())
        .hasValue(companyDefinition);
    assertThat(board.getTool(position.move(0, +1)).getCompanyDefinition())
        .hasValue(companyDefinition);
    assertThat(board.getTool(position.move(+1, -1)).getCompanyDefinition())
        .hasValue(companyDefinition);
    assertThat(board.getTool(position.move(+1, 0)).getCompanyDefinition())
        .hasValue(companyDefinition);
    assertThat(board.getTool(position.move(+1, -1)).getCompanyDefinition())
        .hasValue(companyDefinition);
  }

  @Test
  void doAction3() {
    // .*.    .*.
    // *A& => *A&
    // .+.    .A.
    final Position position = Position.create(5, 5);
    Board board =
        SimpleBoard.newSimpleBoardBuilder()
            .put(position, Tool.newCompanyTool(companyDefinition))
            .put(position.move(-1, -1), Tool.newEmptyTool())
            .put(position.move(-1, 0), Tool.newStarTool())
            .put(position.move(-1, +1), Tool.newEmptyTool())
            .put(position.move(0, -1), Tool.newStarTool())
            .put(position.move(0, +1), Tool.newGoldStarTool())
            .put(position.move(+1, -1), Tool.newEmptyTool())
            .put(position.move(+1, 0), Tool.newHitTool())
            .put(position.move(+1, +1), Tool.newEmptyTool())
            .build();

    int sizeDiff = 1;
    int valueDiff = 2100;

    CompanyUpdateAction companyUpdateAction =
        new CompanyUpdateAction(clientEventSubscriber, board, position, company, players);
    companyUpdateAction.doAction();
    assertThat(company.getSize()).isEqualTo(size + sizeDiff);
    assertThat(company.getValue()).isEqualTo(value + valueDiff);

    assertThat(board.getTool(position.move(-1, -1)).getToolType()).isEqualTo(ToolType.EMPTY);
    assertThat(board.getTool(position.move(-1, 0)).getToolType()).isEqualTo(ToolType.STAR);
    assertThat(board.getTool(position.move(-1, +1)).getToolType()).isEqualTo(ToolType.EMPTY);
    assertThat(board.getTool(position.move(0, -1)).getToolType()).isEqualTo(ToolType.STAR);
    assertThat(board.getTool(position.move(0, 0)).getCompanyDefinition())
        .hasValue(companyDefinition);
    assertThat(board.getTool(position.move(0, +1)).getToolType()).isEqualTo(ToolType.GOLD_STAR);
    assertThat(board.getTool(position.move(+1, -1)).getToolType()).isEqualTo(ToolType.EMPTY);
    assertThat(board.getTool(position.move(+1, 0)).getCompanyDefinition())
        .hasValue(companyDefinition);
    assertThat(board.getTool(position.move(+1, +1)).getToolType()).isEqualTo(ToolType.EMPTY);
  }

  @Test
  void mergedIntoCompanyValueAfterSplit() {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    company.setValue(3200);
    player1.setNumOfStocks(company, 100);
    player2.setNumOfStocks(company, 200);
    CompanyUpdateAction companyUpdateAction =
        new CompanyUpdateAction(clientEventSubscriber, board, position, company, players);
    companyUpdateAction.doAction();

    assertThat(company.getValue()).isEqualTo(1600);
    assertThat(player1.getNumOfStocks(company)).isEqualTo(200);
    assertThat(player2.getNumOfStocks(company)).isEqualTo(400);
  }
}
