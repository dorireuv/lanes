package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import static org.junit.Assert.assertEquals;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.GalacticBombEventDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class GalacticBombActionTest {

  @Mock private ClientEventSubscriber clientEventSubscriber;

  private Company company;

  @BeforeEach
  void setUp() {
    CompanyDefinition companyDefinition = CompanyDefinition.create('A', "A");
    company = new Company(companyDefinition);
    company.setValue(1000);
  }

  @Test
  void doActionWithPositiveEffect() throws Exception {
    GalacticBombEventDefinition galacticBombEventDefinition =
        GalacticBombEventDefinition.create("", 0.1);
    GalacticBombAction galacticBombAction =
        new GalacticBombAction(clientEventSubscriber, company, galacticBombEventDefinition);
    galacticBombAction.doAction();
    assertEquals(company.getValue(), 1100);
  }

  @Test
  void doActionWithNegativeEffect() throws Exception {
    GalacticBombEventDefinition galacticBombEventDefinition =
        GalacticBombEventDefinition.create("", -0.1);
    GalacticBombAction galacticBombAction =
        new GalacticBombAction(clientEventSubscriber, company, galacticBombEventDefinition);
    galacticBombAction.doAction();
    assertEquals(company.getValue(), 900);
  }
}
