package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

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
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Rule;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

class BoardCheckerTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ActionFactory actionFactory;

  private final Position position = Position.create(5, 5);
  private final Position corner = Position.create(0, 0);
  private final Position nearCorner = Position.create(1, 0);
  private final CompanyDefinition companyDefinitionA = CompanyDefinition.create('A', "A");
  private final CompanyDefinition companyDefinitionB = CompanyDefinition.create('B', "B");
  private final CompanyDefinition companyDefinitionC = CompanyDefinition.create('C', "C");
  private final CompanyDefinition companyDefinitionD = CompanyDefinition.create('D', "D");
  private Company companyA;
  private Company companyB;
  private Company companyC;
  private Company companyD;

  private Map<CompanyDefinition, Company> buildCompanies(int... sizes) {
    companyA = new Company(companyDefinitionA);
    companyB = new Company(companyDefinitionB);
    companyC = new Company(companyDefinitionC);
    companyD = new Company(companyDefinitionD);
    List<Company> companies = Arrays.asList(companyA, companyB, companyC, companyD);
    for (int i = 0; i < sizes.length; i++) {
      companies.get(i).setSize(sizes[i]);
    }
    return new HashMap<CompanyDefinition, Company>() {
      {
        put(companyDefinitionA, companyA);
        put(companyDefinitionB, companyB);
        put(companyDefinitionC, companyC);
        put(companyDefinitionD, companyD);
      }
    };
  }

  private Board buildBoard(Map<Position, Tool> positionToToolMap) {
    Board board = new SimpleBoard();
    for (Map.Entry<Position, Tool> entry : positionToToolMap.entrySet()) {
      board.setTool(entry.getKey(), entry.getValue());
    }
    return board;
  }

  @DataProvider(name = "hitDataProvider")
  public Object[][] hitDataProvider() {
    return new Object[][] {
      {
        // ...
        // ._.
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
              }
            }),
        position,
      },
      {
        // _..
        // ...
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
              }
            }),
        corner,
      },
      {
        // _..
        // ...
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
              }
            }),
        nearCorner,
      },
      {
        // +..
        // ._.
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(-1, -1), new HitTool());
              }
            }),
        position,
      },
      {
        // ..+
        // ._.
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(-1, +1), new HitTool());
              }
            }),
        position,
      },
      {
        // ...
        // ._.
        // ..+
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(+1, +1), new HitTool());
              }
            }),
        position,
      },
      {
        // ...
        // ._.
        // +..
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(+1, -1), new HitTool());
              }
            }),
        position,
      },
    };
  }

  @DataProvider(name = "createCompanyDataProvider")
  public Object[][] createCompanyDataProvider() {
    return new Object[][] {
      {
        // ...
        // ._+
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(0, +1), new HitTool());
              }
            }),
        position,
      },
      {
        // ...
        // +_.
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(0, -1), new HitTool());
              }
            }),
        position,
      },
      {
        // .+.
        // ._.
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(-1, 0), new HitTool());
              }
            }),
        position,
      },
      {
        // ...
        // ._.
        // .+.
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(+1, 0), new HitTool());
              }
            }),
        position,
      },
      {
        // ...
        // ._*
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(0, +1), new StarTool());
              }
            }),
        position,
      },
      {
        // ...
        // *_.
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(0, -1), new StarTool());
              }
            }),
        position,
      },
      {
        // .*.
        // ._.
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(-1, 0), new StarTool());
              }
            }),
        position,
      },
      {
        // ...
        // ._.
        // .*.
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(+1, 0), new StarTool());
              }
            }),
        position,
      },
      {
        // ...
        // ._&
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(0, +1), new GoldStarTool());
              }
            }),
        position,
      },
      {
        // ...
        // &_.
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(0, -1), new GoldStarTool());
              }
            }),
        position,
      },
      {
        // .&.
        // ._.
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(-1, 0), new GoldStarTool());
              }
            }),
        position,
      },
      {
        // ...
        // ._.
        // .&.
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(+1, 0), new GoldStarTool());
              }
            }),
        position,
      },
      {
        // _..
        // *..
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(corner.move(+1, 0), new StarTool());
              }
            }),
        corner,
      },
      {
        // _*.
        // ...
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(corner.move(0, +1), new StarTool());
              }
            }),
        corner,
      },
    };
  }

  @DataProvider(name = "growCompanyDataProvider")
  public Object[][] growCompanyDataProvider() {
    return new Object[][] {
      {
        // ...
        // ._A
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(0, +1), new CompanyTool(companyDefinitionA));
              }
            }),
        position,
      },
      {
        // ...
        // A_.
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(0, -1), new CompanyTool(companyDefinitionA));
              }
            }),
        position,
      },
      {
        // .A.
        // ._.
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(-1, 0), new CompanyTool(companyDefinitionA));
              }
            }),
        position,
      },
      {
        // ...
        // ._.
        // .A.
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(+1, 0), new CompanyTool(companyDefinitionA));
              }
            }),
        position,
      },
      {
        // .A.
        // ._A
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(-1, 0), new CompanyTool(companyDefinitionA));
                put(position.move(0, +1), new CompanyTool(companyDefinitionA));
              }
            }),
        position,
      },
      {
        // .A.
        // A_.
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(-1, 0), new CompanyTool(companyDefinitionA));
                put(position.move(0, -1), new CompanyTool(companyDefinitionA));
              }
            }),
        position,
      },
      {
        // ...
        // ._A
        // .A.
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(+1, 0), new CompanyTool(companyDefinitionA));
                put(position.move(0, +1), new CompanyTool(companyDefinitionA));
              }
            }),
        position,
      },
      {
        // ...
        // A_.
        // .A.
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(+1, 0), new CompanyTool(companyDefinitionA));
                put(position.move(0, -1), new CompanyTool(companyDefinitionA));
              }
            }),
        position,
      },
      {
        // ...
        // A_A
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(0, -1), new CompanyTool(companyDefinitionA));
                put(position.move(0, +1), new CompanyTool(companyDefinitionA));
              }
            }),
        position,
      },
      {
        // .A.
        // ._.
        // .A.
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(-1, 0), new CompanyTool(companyDefinitionA));
                put(position.move(+1, 0), new CompanyTool(companyDefinitionA));
              }
            }),
        position,
      },
      {
        // _*.
        // A..
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(corner.move(0, +1), new StarTool());
                put(corner.move(+1, 0), new CompanyTool(companyDefinitionA));
              }
            }),
        corner,
      },
    };
  }

  @Test(dataProvider = "hitDataProvider")
  public void testHit(Board board, Position position) {
    BoardChecker boardChecker = new BoardChecker(actionFactory, board, position, buildCompanies());
    boardChecker.check();
    verify(actionFactory, times(1)).createHitAction();
    verifyZeroInteractions(actionFactory);
  }

  @Test(dataProvider = "createCompanyDataProvider")
  public void testCreateCompany(Board board, Position position) {
    BoardChecker boardChecker = new BoardChecker(actionFactory, board, position, buildCompanies());
    boardChecker.check();
    verify(actionFactory, times(1)).createCreateCompanyAction(any(Company.class));
    verify(actionFactory, times(1)).createCompanyUpdateAction(any(Company.class));
    verifyZeroInteractions(actionFactory);
  }

  @Test
  public void testCreateCompanyUsesFirstNonEmptyCompany() {
    Board board =
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(0, +1), new StarTool());
              }
            });
    Map<CompanyDefinition, Company> companies = buildCompanies();
    companies.get(companyDefinitionA).setSize(1);
    BoardChecker boardChecker = new BoardChecker(actionFactory, board, position, companies);
    boardChecker.check();

    ArgumentCaptor<Company> companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
    verify(actionFactory, times(1)).createCreateCompanyAction(companyArgumentCaptor.capture());
    assertSame(companyArgumentCaptor.getValue(), companyB);
  }

  @Test(dataProvider = "growCompanyDataProvider")
  public void testGrowCompany(Board board, Position position) {
    BoardChecker boardChecker = new BoardChecker(actionFactory, board, position, buildCompanies());
    boardChecker.check();
    verify(actionFactory, times(1)).createGrowCompanyAction(any(Company.class));
    verify(actionFactory, times(1)).createCompanyUpdateAction(any(Company.class));
    verifyZeroInteractions(actionFactory);
  }

  @DataProvider(name = "mergeCompanyDataProvider")
  public Object[][] mergeCompanyDataProvider() {
    return new Object[][] {
      {
        // ...
        // A.B
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(0, -1), new CompanyTool(companyDefinitionA));
                put(position.move(0, +1), new CompanyTool(companyDefinitionB));
              }
            }),
        position,
        buildCompanies(2, 1),
        companyDefinitionA,
        new CompanyDefinition[] {companyDefinitionB},
      },
      {
        // .C.
        // A.B
        // ...
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(0, -1), new CompanyTool(companyDefinitionA));
                put(position.move(0, +1), new CompanyTool(companyDefinitionB));
                put(position.move(-1, 0), new CompanyTool(companyDefinitionC));
              }
            }),
        position,
        buildCompanies(5, 10, 2),
        companyDefinitionB,
        new CompanyDefinition[] {companyDefinitionA, companyDefinitionC},
      },
      {
        // .C.
        // A.B
        // .D.
        buildBoard(
            new HashMap<Position, Tool>() {
              {
                put(position.move(0, -1), new CompanyTool(companyDefinitionA));
                put(position.move(0, +1), new CompanyTool(companyDefinitionB));
                put(position.move(-1, 0), new CompanyTool(companyDefinitionC));
                put(position.move(+1, 0), new CompanyTool(companyDefinitionD));
              }
            }),
        position,
        buildCompanies(5, 10, 2, 3),
        companyDefinitionB,
        new CompanyDefinition[] {companyDefinitionA, companyDefinitionD, companyDefinitionC},
      },
    };
  }

  @Test(dataProvider = "mergeCompanyDataProvider")
  public void testMergeCompany(
      Board board,
      Position position,
      Map<CompanyDefinition, Company> companies,
      CompanyDefinition mergedIntoCompanyDefinition,
      CompanyDefinition[] mergedCompaniesDefinitions) {
    BoardChecker boardChecker = new BoardChecker(actionFactory, board, position, companies);
    boardChecker.check();

    InOrder inOrder = inOrder(actionFactory);
    for (CompanyDefinition mergedCompanyDefinition : mergedCompaniesDefinitions) {
      ArgumentCaptor<Company> mergedIntoCompanyArgument = ArgumentCaptor.forClass(Company.class);
      ArgumentCaptor<Company> mergedCompanyArgument = ArgumentCaptor.forClass(Company.class);
      inOrder
          .verify(actionFactory, calls(1))
          .createMergeCompanyAction(
              mergedIntoCompanyArgument.capture(), mergedCompanyArgument.capture());
      inOrder
          .verify(actionFactory, calls(1))
          .createCompanyUpdateAction(mergedIntoCompanyArgument.capture());
      assertEquals(
          mergedIntoCompanyDefinition, mergedIntoCompanyArgument.getValue().getCompanyDefinition());
      assertEquals(
          mergedCompanyDefinition, mergedCompanyArgument.getValue().getCompanyDefinition());
    }

    verifyZeroInteractions(actionFactory);
  }
}
