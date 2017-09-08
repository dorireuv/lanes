package com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public abstract class JsonStringListLoader<T> implements Loader<List<T>> {
  private final String jsonString;
  private List<T> list;

  protected JsonStringListLoader(String jsonString) {
    this.jsonString = jsonString;
  }

  @Override
  public List<T> load() throws FailedToLoadException {
    tryToReadList();
    return list;
  }

  private void tryToReadList() throws FailedToLoadException {
    try {
      readList();
    } catch (JSONException e) {
      throw new FailedToLoadException("Failed to read list: " + e.getMessage());
    }
  }

  private void readList() throws JSONException {
    JSONArray jsonArray = new JSONArray(jsonString);
    list = new LinkedList<>();
    for (int i = 0; i < jsonArray.length(); i++) {
      list.add(readObject(jsonArray.getJSONObject(i)));
    }
  }

  protected abstract T readObject(JSONObject jsonObject) throws JSONException;
}
