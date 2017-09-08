package com.dorireuv.lanes.com.dorireuv.lanes.core.util.loader;

import com.dorireuv.lanes.com.dorireuv.lanes.core.TestBase;
import org.junit.Test;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.junit.gen5.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FileLoaderDecoratorTest extends TestBase {
  @Mock
  private LoaderProvider<List<String>, String> loaderProvider;
  @Mock
  private Loader<List<String>> loader;
  @Mock
  private BufferedReader bufferedReader;

  @Test
  public void testLoadCallsDecoratedLoader() throws Exception {
    String str = "temp";
    when(loaderProvider.provide(str)).thenReturn(loader);
    when(bufferedReader.readLine()).thenReturn(str).thenReturn(null);
    FileLoaderDecorator<List<String>> fileLoaderDecorator =
        new FileLoaderDecorator<>(loaderProvider, bufferedReader);
    fileLoaderDecorator.load();
    verify(loaderProvider).provide(str);
  }

  @Test
  public void testLoadWhenIOExceptionThrowsException() throws Exception {
    when(bufferedReader.readLine()).thenThrow(new IOException());
    FileLoaderDecorator<List<String>> fileLoaderDecorator =
        new FileLoaderDecorator<>(loaderProvider, bufferedReader);
    assertThrows(FailedToLoadException.class, fileLoaderDecorator::load);
  }
}
