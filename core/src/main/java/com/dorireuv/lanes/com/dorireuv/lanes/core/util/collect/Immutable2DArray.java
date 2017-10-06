package com.dorireuv.lanes.com.dorireuv.lanes.core.util.collect;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import java.util.function.Supplier;

public final class Immutable2DArray<T> {

  private final int rows;
  private final int cols;
  private final ImmutableTable<Integer, Integer, T> data;

  private Immutable2DArray(int rows, int cols, Table<Integer, Integer, T> data) {
    checkArgument(rows > 0);
    checkArgument(cols > 0);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        checkNotNull(data.get(i, j));
      }
    }

    this.rows = rows;
    this.cols = cols;
    this.data = ImmutableTable.copyOf(data);
  }

  public T get(int row, int col) {
    checkArgument(row >= 0 && row < rows);
    checkArgument(col >= 0 && col < cols);
    return data.get(row, col);
  }

  public int rows() {
    return rows;
  }

  public int cols() {
    return cols;
  }

  public static <T> Builder<T> builder(int rows, int cols) {
    return new Builder<>(rows, cols);
  }

  public static class Builder<T> {
    private final int rows;
    private final int cols;
    private final Table<Integer, Integer, T> data;
    private Supplier<T> defaultValue;

    private Builder(int rows, int cols) {
      checkArgument(rows > 0);
      checkArgument(cols > 0);

      this.rows = rows;
      this.cols = cols;

      data = HashBasedTable.create();
    }

    public Builder<T> setDefaultValue(Supplier<T> defaultValue) {
      this.defaultValue = checkNotNull(defaultValue);
      return this;
    }

    public Builder<T> put(int row, int col, T value) {
      checkArgument(row >= 0 && row < rows);
      checkArgument(col >= 0 && col < cols);

      data.put(row, col, value);

      return this;
    }

    public Immutable2DArray<T> build() {
      checkNotNull(defaultValue);

      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          if (!data.contains(i, j)) {
            data.put(i, j, defaultValue.get());
          }
        }
      }

      return new Immutable2DArray<>(rows, cols, data);
    }
  }
}
