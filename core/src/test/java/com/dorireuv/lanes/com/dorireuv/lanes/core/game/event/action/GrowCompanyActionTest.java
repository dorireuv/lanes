package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import static com.google.common.truth.Truth8.assertThat;
import static org.junit.Assert.assertEquals;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class GrowCompanyActionTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ClientEventSubscriber clientEventSubscriber;

  @Test
  public void doAction() throws Exception {
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    CompanyDefinition companyDefinition = CompanyDefinition.create('A', "A");
    int size = 4;
    int value = 1000;
    Company company = new Company(companyDefinition, size, value);
    GrowCompanyAction growCompanyAction =
        new GrowCompanyAction(clientEventSubscriber, board, position, company);
    growCompanyAction.doAction();
    assertEquals(company.getSize(), size + 1);
    Tool tool = board.getTool(position);
    assertThat(tool.getCompanyDefinition()).hasValue(companyDefinition);
  }
}
