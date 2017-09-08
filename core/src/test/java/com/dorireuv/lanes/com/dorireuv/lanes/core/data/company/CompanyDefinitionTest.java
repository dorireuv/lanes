package com.dorireuv.lanes.com.dorireuv.lanes.core.data.company;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public final class CompanyDefinitionTest {

  @Test
  public void compare_aToA_aEqualToA() {
    CompanyDefinition a1 = CompanyDefinition.create('A', "A1");
    CompanyDefinition a2 = CompanyDefinition.create('A', "A2");
    int res = a1.compareTo(a2);
    assertThat(res).isEqualTo(0);
  }

  @Test
  public void compare_aToB_aLessThanB() {
    CompanyDefinition a = CompanyDefinition.create('A', "A");
    CompanyDefinition b = CompanyDefinition.create('B', "B");
    int res = a.compareTo(b);
    assertThat(res).isEqualTo(-1);
  }

  @Test
  public void compare_bToA_bGreaterThanA() {
    CompanyDefinition a = CompanyDefinition.create('A', "A");
    CompanyDefinition b = CompanyDefinition.create('B', "B");
    int res = b.compareTo(a);
    assertThat(res).isEqualTo(+1);
  }
}
