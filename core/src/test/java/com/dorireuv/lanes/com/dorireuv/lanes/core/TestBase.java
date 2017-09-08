package com.dorireuv.lanes.com.dorireuv.lanes.core;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class TestBase {

  @Before
  public void injectDoubles() {
    MockitoAnnotations.initMocks(this); // This could be pulled up into a shared base class
  }
}
