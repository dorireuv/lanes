package com.dorireuv.lanes.com.dorireuv.lanes.core.config;

import org.junit.Assert;
import org.junit.Test;

public class ConfigTest {

  @Test
  public void create() throws Exception {
    Assert.assertNotNull(Config.getSellCommission());
  }
}
