package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.CompanyTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.Player;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.player.SimplePlayer;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class CreateCompanyActionTest extends TestBase {

  @Mock
  private ClientEventSubscriber clientEventSubscriber;

  @Test
  public void testDoAction() throws Exception {
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
    CompanyTool companyTool = (CompanyTool) board.getTool(position);
    assertEquals(companyTool.getData().getCompanyDefinition(), companyDefinition);
  }
}
