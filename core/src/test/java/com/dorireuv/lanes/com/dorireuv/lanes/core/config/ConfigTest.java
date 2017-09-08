package com.dorireuv.lanes.com.dorireuv.lanes.core.config;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import org.junit.Assert;
import org.junit.Test;

public class ConfigTest extends TestBase {

  @Test
  public void testCreate() throws Exception {
    Assert.assertNotNull(Config.getSellCommission());
  }
}
