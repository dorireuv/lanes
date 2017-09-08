package com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader;

public interface LoaderProvider<T, S> {
  Loader<T> provide(S input);
}
