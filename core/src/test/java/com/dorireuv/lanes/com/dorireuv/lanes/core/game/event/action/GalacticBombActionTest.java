package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.GalacticBombEventDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class GalacticBombActionTest extends TestBase {

  @Mock
  private ClientEventSubscriber clientEventSubscriber;

  private Company company;

  @Before
  public void setUp() {
    CompanyDefinition companyDefinition = CompanyDefinition.create('A', "A");
    company = new Company(companyDefinition);
    company.setValue(1000);
  }

  @Test
  public void testDoActionWithPositiveEffect() throws Exception {
    GalacticBombEventDefinition galacticBombEventDefinition =
        GalacticBombEventDefinition.create("", 0.1);
    GalacticBombAction galacticBombAction =
        new GalacticBombAction(clientEventSubscriber, company, galacticBombEventDefinition);
    galacticBombAction.doAction();
    assertEquals(company.getValue(), 1100);
  }

  @Test
  public void testDoActionWithNegativeEffect() throws Exception {
    GalacticBombEventDefinition galacticBombEventDefinition =
        GalacticBombEventDefinition.create("", -0.1);
    GalacticBombAction galacticBombAction =
        new GalacticBombAction(clientEventSubscriber, company, galacticBombEventDefinition);
    galacticBombAction.doAction();
    assertEquals(company.getValue(), 900);
  }
}
