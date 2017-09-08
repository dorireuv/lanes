package com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader;

import java.io.BufferedReader;
import java.io.IOException;

public class FileLoaderDecorator<T> implements Loader<T> {
  private final LoaderProvider<T, String> loaderProvider;
  private final BufferedReader reader;
  private String inputStreamContent;
  private T results;

  public FileLoaderDecorator(LoaderProvider<T, String> loaderProvider, BufferedReader reader) {
    this.loaderProvider = loaderProvider;
    this.reader = reader;
  }

  @Override
  public T load() throws FailedToLoadException {
    tryToReadInputStream();
    tryToLoad();
    return results;
  }

  private void tryToReadInputStream() throws FailedToLoadException {
    try {
      readInputStream();
    } catch (IOException e) {
      throw new FailedToLoadException("Failed to read input stream: " + e.getMessage());
    }
  }

  private void readInputStream() throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      stringBuilder.append(line);
    }

    inputStreamContent = stringBuilder.toString();
  }

  private void tryToLoad() throws FailedToLoadException {
    results = loaderProvider.provide(inputStreamContent).load();
  }
}
