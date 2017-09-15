package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import java.util.Map;

public interface Board {

  int getRows();

  int getCols();

  Tool getTool(Position position);

  Tool getTool(int row, int col);

  Tool getToolWithoutBoundProtection(Position position);

  void setTool(Position position, Tool tool);

  Map<Position, Tool> getToolsAround(Position centerPosition);
}
