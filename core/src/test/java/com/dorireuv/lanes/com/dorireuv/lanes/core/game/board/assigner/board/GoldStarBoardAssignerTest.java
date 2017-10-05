package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board;

import static com.google.common.truth.Truth.assertThat;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.tool.Tool;
import org.junit.Before;
import org.junit.Test;

public class GoldStarBoardAssignerTest {
  private Board board;
  private GoldStarBoardAssigner goldStarBoardAssigner;

  @Before
  public void setUp() {
    board = new SimpleBoard();
    goldStarBoardAssigner = new GoldStarBoardAssigner();
  }

  @Test
  public void assignPosition_empty_accepted() {
    Position position = Position.create(5, 5);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isTrue();
  }

  @Test
  public void assignPosition_firstRow_rejected() {
    Position position = Position.create(0, 5);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_lastRow_rejected() {
    Position position = Position.create(board.getRows() - 1, 5);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_firstColumn_rejected() {
    Position position = Position.create(5, 0);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_secondColumn_rejected() {
    Position position = Position.create(5, 1);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_lastColumn_rejected() {
    Position position = Position.create(5, board.getCols() - 1);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_beforeLastColumn_rejected() {
    Position position = Position.create(5, board.getCols() - 2);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_onGoldStarTool_rejected() {
    Position position = Position.create(5, 5);
    board.setTool(position, Tool.newGoldStarTool());

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_goldStarUp_rejected() {
    Position position = Position.create(5, 5);
    board.setTool(position.move(-1, 0), Tool.newGoldStarTool());

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_goldStarDown_rejected() {
    Position position = Position.create(5, 5);
    board.setTool(position.move(+1, 0), Tool.newGoldStarTool());

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_goldStarLeft_rejected() {
    Position position = Position.create(5, 5);
    board.setTool(position.move(0, -1), Tool.newGoldStarTool());

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_goldStarRight_rejected() {
    Position position = Position.create(5, 5);
    board.setTool(position.move(0, +1), Tool.newGoldStarTool());

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_threeStarsUp_rejected() {
    Position position = Position.create(5, 5);
    board.setTool(position.move(-1, 0), Tool.newStarTool());
    board.setTool(position.move(0, +1), Tool.newStarTool());
    board.setTool(position.move(0, -1), Tool.newStarTool());

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_threeStarsDown_rejected() {
    Position position = Position.create(5, 5);
    board.setTool(position.move(+1, 0), Tool.newStarTool());
    board.setTool(position.move(0, +1), Tool.newStarTool());
    board.setTool(position.move(0, -1), Tool.newStarTool());

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_threeStarsLeft_rejected() {
    Position position = Position.create(5, 5);
    board.setTool(position.move(0, -1), Tool.newStarTool());
    board.setTool(position.move(+1, 0), Tool.newStarTool());
    board.setTool(position.move(-1, 0), Tool.newStarTool());

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_threeStarsRight_rejected() {
    Position position = Position.create(5, 5);
    board.setTool(position.move(0, +1), Tool.newStarTool());
    board.setTool(position.move(+1, 0), Tool.newStarTool());
    board.setTool(position.move(-1, 0), Tool.newStarTool());

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  public void assignPosition_fourStars_rejected() {
    Position position = Position.create(5, 5);
    board.setTool(position.move(0, -1), Tool.newStarTool());
    board.setTool(position.move(0, +1), Tool.newStarTool());
    board.setTool(position.move(+1, 0), Tool.newStarTool());
    board.setTool(position.move(-1, 0), Tool.newStarTool());

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }
}
