package com.dorireuv.lanes.com.dorireuv.lanes.core.data.company;

import com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader.JsonStringListLoader;
import org.json.JSONException;
import org.json.JSONObject;

class JsonStringCompanyLoader extends JsonStringListLoader<CompanyDefinition> {

  public JsonStringCompanyLoader(String jsonString) {
    super(jsonString);
  }

  @Override
  protected CompanyDefinition readObject(JSONObject jsonObject) throws JSONException {
    return CompanyDefinition.create(
        jsonObject.getString("symbol").charAt(0), jsonObject.getString("name"));
  }
}
