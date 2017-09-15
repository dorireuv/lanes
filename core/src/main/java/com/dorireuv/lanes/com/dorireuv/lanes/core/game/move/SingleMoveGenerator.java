package com.dorireuv.lanes.com.dorireuv.lanes.core.game.move;

import static java.util.stream.Collectors.toMap;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SingleMoveGenerator {

  private final RandomWrapper randomWrapper;

  public SingleMoveGenerator(RandomWrapper randomWrapper) {
    this.randomWrapper = randomWrapper;
  }

  Position generate(Board board, boolean freeCompanyExist) {
    Position candidate = randomWrapper.nextPosition(board);
    Tool tool = board.getTool(candidate);
    if (!tool.getToolType().equals(ToolType.EMPTY)) {
      return null;
    }

    // check that it's not near a star/plus
    if (!freeCompanyExist) {
      List<Position> surround =
          Arrays.asList(
              candidate.move(-1, 0),
              candidate.move(+1, 0),
              candidate.move(0, -1),
              candidate.move(0, +1));

      Map<Position, Tool> positionToToolMap = new HashMap<>();
      for (Position position : surround) {
        positionToToolMap.put(position, board.getToolWithoutBoundProtection(position));
      }

      Map<Position, Tool> positionToStarMap =
          positionToToolMap
              .entrySet()
              .stream()
              .filter(e -> e.getValue().getToolType().isStar())
              .collect(toMap(Map.Entry::getKey, Entry::getValue));
      Map<Position, Tool> positionToCompanyMap =
          positionToToolMap
              .entrySet()
              .stream()
              .filter(e -> e.getValue().getToolType().equals(ToolType.COMPANY))
              .collect(toMap(Map.Entry::getKey, Entry::getValue));

      if (positionToCompanyMap.size() == 0 && positionToStarMap.size() > 0) {
        return null;
      }
    }

    return candidate;
  }
}
