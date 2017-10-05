package com.dorireuv.lanes.com.dorireuv.lanes.core.game.bank;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.dorireuv.lanes.com.dorireuv.lanes.core.client.event.ClientEventSubscriber;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class BankClientDecoratorTest {

  @Mock private ClientEventSubscriber clientEventSubscriber;

  private BankClientDecorator bankClientDecorator;

  @BeforeEach
  void setUp() {
    Bank bank = new SimpleBank();
    bankClientDecorator = new BankClientDecorator(bank, clientEventSubscriber);
  }

  @Test
  void getCashMoney() throws Exception {
    bankClientDecorator.getCashMoney();
    verifyNoMoreInteractions(clientEventSubscriber);
  }

  @Test
  void addZeroCashMoneyDispatchesEvent() throws Exception {
    bankClientDecorator.addCashMoney(0);
    verifyNoMoreInteractions(clientEventSubscriber);
  }

  @Test
  void addNonZeroCashMoneyDoesNotDispatchEvent() throws Exception {
    bankClientDecorator.addCashMoney(-1);
    verify(clientEventSubscriber, times(1)).onBankCashMoneyChange(any());
    verifyNoMoreInteractions(clientEventSubscriber);
  }
}
