package com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.BankCashMoneyChangeEvent;
import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class BankClientDecoratorTest {

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private ClientEventSubscriber clientEventSubscriber;

  private BankClientDecorator bankClientDecorator;

  @Before
  public void setUp() {
    Bank bank = new SimpleBank();
    bankClientDecorator = new BankClientDecorator(bank, clientEventSubscriber);
  }

  @Test
  public void testGetCashMoney() throws Exception {
    bankClientDecorator.getCashMoney();
    verifyNoMoreInteractions(clientEventSubscriber);
  }

  @Test
  public void testAddZeroCashMoneyDispatchesEvent() throws Exception {
    bankClientDecorator.addCashMoney(0);
    verifyNoMoreInteractions(clientEventSubscriber);
  }

  @Test
  public void testAddNonZeroCashMoneyDoesNotDispatchEvent() throws Exception {
    bankClientDecorator.addCashMoney(-1);
    verify(clientEventSubscriber, times(1))
        .onBankCashMoneyChange(any(BankCashMoneyChangeEvent.class));
    verifyNoMoreInteractions(clientEventSubscriber);
  }
}
