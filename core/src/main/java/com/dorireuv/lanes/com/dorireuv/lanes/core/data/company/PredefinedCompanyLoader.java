package com.dorireuv.lanes.com.dorireuv.lanes.core.data.company;

import com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader.PredefinedListLoader;
import java.util.List;

public class PredefinedCompanyLoader extends PredefinedListLoader<List<CompanyDefinition>> {

  public PredefinedCompanyLoader() {
    super(
        JsonStringCompanyLoader::new,
        PredefinedCompanyLoader.class.getResourceAsStream("/companies.json"));
  }
}
