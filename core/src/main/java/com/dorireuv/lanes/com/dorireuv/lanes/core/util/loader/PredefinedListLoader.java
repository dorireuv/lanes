package com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class PredefinedListLoader<T> implements Loader<T> {

  private final LoaderProvider<T, String> loaderProvider;
  private final InputStream inputStream;

  protected PredefinedListLoader(
      LoaderProvider<T, String> loaderProvider, InputStream inputStream) {
    this.loaderProvider = loaderProvider;
    this.inputStream = inputStream;
  }

  @Override
  public T load() throws FailedToLoadException {
    try {
      FileLoaderDecorator<T> fileLoader =
          new FileLoaderDecorator<>(
              loaderProvider, new BufferedReader(new InputStreamReader(inputStream, "UTF-8")));

      return fileLoader.load();
    } catch (UnsupportedEncodingException e) {
      throw new FailedToLoadException(e.getMessage());
    }
  }
}
