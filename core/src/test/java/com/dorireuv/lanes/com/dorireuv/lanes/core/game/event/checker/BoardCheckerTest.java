package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.checker;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.Action;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action.ActionFactory;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class BoardCheckerTest {

  @Mock private ActionFactory actionFactory;
  @Mock private Action action;

  @BeforeEach
  void setUp() {
    when(actionFactory.createCreateCompanyAction(any())).thenReturn(action);
    when(actionFactory.createCompanyUpdateAction(any())).thenReturn(action);
  }

  private static final Position POSITION = Position.create(5, 5);
  private static final Position CORNER = Position.create(0, 0);
  private static final Position NEAR_CORNER = Position.create(1, 0);
  private static final CompanyDefinition COMPANY_DEFINITION_A = CompanyDefinition.create('A', "A");
  private static final CompanyDefinition COMPANY_DEFINITION_B = CompanyDefinition.create('B', "B");
  private static final CompanyDefinition COMPANY_DEFINITION_C = CompanyDefinition.create('C', "C");
  private static final CompanyDefinition COMPANY_DEFINITION_D = CompanyDefinition.create('D', "D");
  private Company companyA;
  private Company companyB;
  private Company companyC;
  private Company companyD;

  private Map<CompanyDefinition, Company> buildCompanies(ImmutableList<Integer> sizes) {
    companyA = new Company(COMPANY_DEFINITION_A);
    companyB = new Company(COMPANY_DEFINITION_B);
    companyC = new Company(COMPANY_DEFINITION_C);
    companyD = new Company(COMPANY_DEFINITION_D);
    List<Company> companies = Arrays.asList(companyA, companyB, companyC, companyD);
    for (int i = 0; i < sizes.size(); i++) {
      companies.get(i).setSize(sizes.get(i));
    }
    return new HashMap<CompanyDefinition, Company>() {
      {
        put(COMPANY_DEFINITION_A, companyA);
        put(COMPANY_DEFINITION_B, companyB);
        put(COMPANY_DEFINITION_C, companyC);
        put(COMPANY_DEFINITION_D, companyD);
      }
    };
  }

  private static Stream<Arguments> hitDataProvider() {
    return Stream.of(
        Arguments.of(
            // ...
            // ._.
            // ...
            SimpleBoard.newSimpleBoardBuilder().build(), POSITION),
        Arguments.of(
            // _..
            // ...
            // ...
            SimpleBoard.newSimpleBoardBuilder().build(), CORNER),
        Arguments.of(
            // _..
            // ...
            // ...
            SimpleBoard.newSimpleBoardBuilder().build(), NEAR_CORNER),
        Arguments.of(

            // +..
            // ._.
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(-1, -1), Tool.newHitTool())
                .build(),
            POSITION),
        Arguments.of(
            // ..+
            // ._.
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(-1, +1), Tool.newHitTool())
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // ._.
            // ..+
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(+1, +1), Tool.newHitTool())
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // ._.
            // +..
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(+1, -1), Tool.newHitTool())
                .build(),
            POSITION));
  }

  private static Stream<Arguments> createCompanyDataProvider() {
    return Stream.of(
        Arguments.of(
            // ...
            // ._+
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(0, +1), Tool.newHitTool())
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // +_.
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(0, -1), Tool.newHitTool())
                .build(),
            POSITION),
        Arguments.of(
            // .+.
            // ._.
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(-1, 0), Tool.newHitTool())
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // ._.
            // .+.
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(+1, 0), Tool.newHitTool())
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // ._*
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(0, +1), Tool.newStarTool())
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // *_.
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(0, -1), Tool.newStarTool())
                .build(),
            POSITION),
        Arguments.of(
            // .*.
            // ._.
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(-1, 0), Tool.newStarTool())
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // ._.
            // .*.
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(+1, 0), Tool.newStarTool())
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // ._&
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(0, +1), Tool.newGoldStarTool())
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // &_.
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(0, -1), Tool.newGoldStarTool())
                .build(),
            POSITION),
        Arguments.of(
            // .&.
            // ._.
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(-1, 0), Tool.newGoldStarTool())
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // ._.
            // .&.
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(+1, 0), Tool.newGoldStarTool())
                .build(),
            POSITION),
        Arguments.of(
            // _..
            // *..
            // ...
            SimpleBoard.newSimpleBoardBuilder().put(CORNER.move(+1, 0), Tool.newStarTool()).build(),
            CORNER),
        Arguments.of(
            // _*.
            // ...
            // ...
            SimpleBoard.newSimpleBoardBuilder().put(CORNER.move(0, +1), Tool.newStarTool()).build(),
            CORNER));
  }

  private static Stream<Arguments> growCompanyDataProvider() {
    return Stream.of(
        Arguments.of(
            // ...
            // ._A
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(0, +1), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // A_.
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(0, -1), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .build(),
            POSITION),
        Arguments.of(
            // .A.
            // ._.
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(-1, 0), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // ._.
            // .A.
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(+1, 0), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .build(),
            POSITION),
        Arguments.of(
            // .A.
            // ._A
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(-1, 0), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .build(),
            POSITION),
        Arguments.of(
            // .A.
            // A_.
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(-1, 0), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .put(POSITION.move(0, -1), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // ._A
            // .A.
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(+1, 0), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .put(POSITION.move(0, +1), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // A_.
            // .A.
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(+1, 0), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .put(POSITION.move(0, -1), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .build(),
            POSITION),
        Arguments.of(
            // ...
            // A_A
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(0, -1), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .put(POSITION.move(0, +1), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .build(),
            POSITION),
        Arguments.of(
            // .A.
            // ._.
            // .A.
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(-1, 0), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .put(POSITION.move(+1, 0), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .build(),
            POSITION),
        Arguments.of(
            // _*.
            // A..
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(CORNER.move(0, +1), Tool.newStarTool())
                .put(CORNER.move(+1, 0), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .build(),
            CORNER));
  }

  @ParameterizedTest
  @MethodSource("hitDataProvider")
  void hit(Board board, Position position) {
    BoardChecker boardChecker =
        new BoardChecker(actionFactory, board, position, buildCompanies(ImmutableList.of()));
    boardChecker.check();
    verify(actionFactory, times(1)).createHitAction();
    verifyZeroInteractions(actionFactory);
  }

  @ParameterizedTest
  @MethodSource("createCompanyDataProvider")
  void createCompany(Board board, Position position) {
    BoardChecker boardChecker =
        new BoardChecker(actionFactory, board, position, buildCompanies(ImmutableList.of()));
    boardChecker.check();
    verify(actionFactory, times(1)).createCreateCompanyAction(any());
    verify(actionFactory, times(1)).createCompanyUpdateAction(any());
    verifyZeroInteractions(actionFactory);
  }

  @Test
  void createCompanyUsesFirstNonEmptyCompany() {
    Board board =
        SimpleBoard.newSimpleBoardBuilder().put(POSITION.move(0, +1), Tool.newStarTool()).build();
    Map<CompanyDefinition, Company> companies = buildCompanies(ImmutableList.of());
    companies.get(COMPANY_DEFINITION_A).setSize(1);
    BoardChecker boardChecker = new BoardChecker(actionFactory, board, POSITION, companies);
    boardChecker.check();

    ArgumentCaptor<Company> companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
    verify(actionFactory, times(1)).createCreateCompanyAction(companyArgumentCaptor.capture());
    assertThat(companyArgumentCaptor.getValue()).isSameAs(companyB);
  }

  @ParameterizedTest
  @MethodSource("growCompanyDataProvider")
  void growCompany(Board board, Position position) {
    BoardChecker boardChecker =
        new BoardChecker(actionFactory, board, position, buildCompanies(ImmutableList.of()));
    boardChecker.check();
    verify(actionFactory, times(1)).createGrowCompanyAction(any());
    verify(actionFactory, times(1)).createCompanyUpdateAction(any());
    verifyZeroInteractions(actionFactory);
  }

  private static Stream<Arguments> mergeCompanyDataProvider() {
    return Stream.of(
        Arguments.of(
            // ...
            // A.B
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(0, -1), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .put(POSITION.move(0, +1), Tool.newCompanyTool(COMPANY_DEFINITION_B))
                .build(),
            POSITION,
            ImmutableList.of(2, 1),
            COMPANY_DEFINITION_A,
            new CompanyDefinition[] {COMPANY_DEFINITION_B}),
        Arguments.of(
            // .C.
            // A.B
            // ...
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(0, -1), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .put(POSITION.move(0, +1), Tool.newCompanyTool(COMPANY_DEFINITION_B))
                .put(POSITION.move(-1, 0), Tool.newCompanyTool(COMPANY_DEFINITION_C))
                .build(),
            POSITION,
            ImmutableList.of(5, 10, 2),
            COMPANY_DEFINITION_B,
            new CompanyDefinition[] {COMPANY_DEFINITION_A, COMPANY_DEFINITION_C}),
        Arguments.of(
            // .C.
            // A.B
            // .D.
            SimpleBoard.newSimpleBoardBuilder()
                .put(POSITION.move(0, -1), Tool.newCompanyTool(COMPANY_DEFINITION_A))
                .put(POSITION.move(0, +1), Tool.newCompanyTool(COMPANY_DEFINITION_B))
                .put(POSITION.move(-1, 0), Tool.newCompanyTool(COMPANY_DEFINITION_C))
                .put(POSITION.move(+1, 0), Tool.newCompanyTool(COMPANY_DEFINITION_D))
                .build(),
            POSITION,
            ImmutableList.of(5, 10, 2, 3),
            COMPANY_DEFINITION_B,
            new CompanyDefinition[] {
              COMPANY_DEFINITION_A, COMPANY_DEFINITION_D, COMPANY_DEFINITION_C
            }));
  }

  @ParameterizedTest
  @MethodSource("mergeCompanyDataProvider")
  void mergeCompany(
      Board board,
      Position position,
      ImmutableList<Integer> companiesSizes,
      CompanyDefinition mergedIntoCompanyDefinition,
      CompanyDefinition[] mergedCompaniesDefinitions) {
    Map<CompanyDefinition, Company> companies = buildCompanies(companiesSizes);
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
