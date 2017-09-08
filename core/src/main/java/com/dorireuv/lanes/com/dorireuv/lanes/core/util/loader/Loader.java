package com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader;

public interface Loader<T> {

  T load() throws FailedToLoadException;
}
