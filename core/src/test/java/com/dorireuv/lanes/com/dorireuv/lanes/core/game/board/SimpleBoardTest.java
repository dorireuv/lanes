package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import static com.google.common.truth.Truth.assertThat;

import com.dorireuv.lanes.com.dorireuv.lanes.core.data.company.CompanyDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ImmutableTool;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.ToolType;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class SimpleBoardTest {

  private Board board;

  @BeforeEach
  void setUp() {
    board = new SimpleBoard();
  }

  @Test
  void create() throws Exception {
    assertThat(board).isNotNull();
  }

  @Test
  void getToolWithoutBoundProtectionWhenOutOfBoundsReturnsEmptyTool() {
    assertThat(board.getToolWithoutBoundProtection(Position.create(-1, -1)).getToolType())
        .isEqualTo(ToolType.EMPTY);
  }

  @Test
  void getToolWithoutBoundProtectionWhenInBoundsReturnsTool() {
    Position position = Position.create(2, 1);
    board.setStar(position);
    assertThat(board.getToolWithoutBoundProtection(position).getToolType())
        .isEqualTo(ToolType.STAR);
  }

  @Test
  void getToolsAround() throws Exception {
    Position position = Position.create(5, 5);
    Position leftPosition = position.move(0, -1);
    Position rightPosition = position.move(0, +1);
    Position topPosition = position.move(-1, 0);
    Position bottomPosition = position.move(+1, 0);

    board.setStar(leftPosition);
    board.setHit(rightPosition);
    board.setGoldStar(topPosition);
    board.setCompany(bottomPosition, CompanyDefinition.create('A', "A"));

    Map<Position, ImmutableTool> toolsAround = board.getToolsAround(position);

    assertThat(toolsAround.get(leftPosition).getToolType()).isEqualTo(ToolType.STAR);
    assertThat(toolsAround.get(rightPosition).getToolType()).isEqualTo(ToolType.HIT);
    assertThat(toolsAround.get(topPosition).getToolType()).isEqualTo(ToolType.GOLD_STAR);
    assertThat(toolsAround.get(bottomPosition).getToolType()).isEqualTo(ToolType.COMPANY);
  }
}
