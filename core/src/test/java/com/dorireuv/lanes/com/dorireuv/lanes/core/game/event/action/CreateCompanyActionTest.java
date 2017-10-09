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
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
final class CreateCompanyActionTest {

  @Mock private ClientEventSubscriber clientEventSubscriber;

  @Test
  void doAction() throws Exception {
    Player player = new SimplePlayer(0, "name", 6000);
    Board board = new SimpleBoard();
    Position position = Position.create(5, 5);
    CompanyDefinition companyDefinition = CompanyDefinition.create('A', "A");
    Company company = new Company(companyDefinition);
    CreateCompanyAction createCompanyAction =
        new CreateCompanyAction(clientEventSubscriber, player, board, position, company);
    createCompanyAction.doAction();
    assertEquals(company.getSize(), 1);
    assertEquals(company.getValue(), 100);
    Tool companyTool = board.getTool(position);
    assertThat(companyTool.getCompanyDefinition()).hasValue(companyDefinition);
  }
}
