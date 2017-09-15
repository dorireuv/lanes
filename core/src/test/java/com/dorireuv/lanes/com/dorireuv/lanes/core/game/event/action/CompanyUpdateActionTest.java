package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import static org.junit.Assert.assertEquals;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.CompanyTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.GoldStarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.HitTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.StarTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CompanyUpdateActionTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ClientEventSubscriber clientEventSubscriber;

  private final CompanyDefinition companyDefinition = CompanyDefinition.create('A', "A");
  private final int size = 10;
  private final int value = 100;
  private Company company;
  private Player player1;
  private Player player2;
  private List<Player> players;

  @Before
  public void setUp() {
    company = new Company(companyDefinition, size, value);
    player1 = new SimplePlayer(0, "A", 0);
    player2 = new SimplePlayer(1, "B", 0);
    players = Arrays.asList(player1, player2);
  }

  private Board buildBoard(Map<Position, Tool> positionToToolMap) {
    Board board = new SimpleBoard();
    for (Map.Entry<Position, Tool> entry : positionToToolMap.entrySet()) {
      board.setTool(entry.getKey(), entry.getValue());
    }
    return board;
  }

  @DataProvider(name = "doActionDataProvider")
  public Object[][] checkDataProvider() {
    final Position position = Position.create(5, 5);
    return new Object[][] {
      {
        // ...
        // .A.
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position, new CompanyTool(companyDefinition));
              }
            }),
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position, new CompanyTool(companyDefinition));
              }
            }),
        position,
        0,
        0,
      },
      {
        // +++    +A+
        // +A+ => AAA
        // +++    +A+
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position, new CompanyTool(companyDefinition));
                put(position.move(-1, -1), new HitTool());
                put(position.move(-1, 0), new HitTool());
                put(position.move(-1, +1), new HitTool());
                put(position.move(0, -1), new HitTool());
                put(position.move(0, +1), new HitTool());
                put(position.move(+1, -1), new HitTool());
                put(position.move(+1, 0), new HitTool());
                put(position.move(+1, +1), new HitTool());
              }
            }),
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position, new CompanyTool(companyDefinition));
                put(position.move(-1, -1), new HitTool());
                put(position.move(-1, 0), new CompanyTool(companyDefinition));
                put(position.move(-1, +1), new HitTool());
                put(position.move(0, -1), new CompanyTool(companyDefinition));
                put(position.move(0, +1), new CompanyTool(companyDefinition));
                put(position.move(+1, -1), new HitTool());
                put(position.move(+1, 0), new CompanyTool(companyDefinition));
                put(position.move(+1, +1), new HitTool());
              }
            }),
        position,
        4,
        400,
      },
      {
        // +*+    +*+
        // *A& => *A&
        // +++    +A+
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position, new CompanyTool(companyDefinition));
                put(position.move(-1, -1), new HitTool());
                put(position.move(-1, 0), new StarTool());
                put(position.move(-1, +1), new HitTool());
                put(position.move(0, -1), new StarTool());
                put(position.move(0, +1), new GoldStarTool());
                put(position.move(+1, -1), new HitTool());
                put(position.move(+1, 0), new HitTool());
                put(position.move(+1, +1), new HitTool());
              }
            }),
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position, new CompanyTool(companyDefinition));
                put(position.move(-1, -1), new HitTool());
                put(position.move(-1, 0), new StarTool());
                put(position.move(-1, +1), new HitTool());
                put(position.move(0, -1), new StarTool());
                put(position.move(0, +1), new GoldStarTool());
                put(position.move(+1, -1), new HitTool());
                put(position.move(+1, 0), new CompanyTool(companyDefinition));
                put(position.move(+1, +1), new HitTool());
              }
            }),
        position,
        1,
        2100,
      },
    };
  }

  @Test(dataProvider = "doActionDataProvider")
  public void testDoAction(
      Board board, Board expectedBoard, Position position, int sizeDiff, int valueDiff) {
    CompanyUpdateAction companyUpdateAction =
        new CompanyUpdateAction(clientEventSubscriber, board, position, company, players);
    companyUpdateAction.doAction();
    assertEquals(company.getSize(), size + sizeDiff);
    assertEquals(company.getValue(), value + valueDiff);
    assertEquals(board, expectedBoard);
  }

  @Test
  public void testMergedIntoCompanyValueAfterSplit() {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    company.setValue(3200);
    player1.setNumOfStocks(company, 100);
    player2.setNumOfStocks(company, 200);
    CompanyUpdateAction companyUpdateAction =
        new CompanyUpdateAction(clientEventSubscriber, board, position, company, players);
    companyUpdateAction.doAction();

    assertEquals(company.getValue(), 1600);
    assertEquals(player1.getNumOfStocks(company), 200);
    assertEquals(player2.getNumOfStocks(company), 400);
  }
}
