package com.dorireuv.lanes.com.dorireuv.lanes.core.data.event;

import com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader.JsonStringListLoader;
import org.json.JSONException;
import org.json.JSONObject;

class JsonStringEventLoader extends JsonStringListLoader<GalacticBombEventDefinition> {

  public JsonStringEventLoader(String jsonString) {
    super(jsonString);
  }

  @Override
  protected GalacticBombEventDefinition readObject(JSONObject jsonObject) throws JSONException {
    return GalacticBombEventDefinition.create(
        jsonObject.getString("text"), jsonObject.getDouble("effect"));
  }
}
