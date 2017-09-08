package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.data.Data;

public interface Tool {

  boolean isEmpty();

  Data getData();

  boolean isFreeze();

  void setIsFreeze(boolean isFreeze);

  boolean isTrap();

  void setIsTrap(boolean isTrap);

  boolean isDoublePayment();

  void setIsDoublePayment(boolean isDoublePayment);
}
