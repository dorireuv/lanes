package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner;

import static com.google.common.truth.Truth.assertThat;

import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Board;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.SimpleBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class GoldStarBoardAssignerTest {
  private Board board;
  private GoldStarBoardAssigner goldStarBoardAssigner;

  @BeforeEach
  void setUp() {
    board = new SimpleBoard();
    goldStarBoardAssigner = new GoldStarBoardAssigner();
  }

  @Test
  void assignPosition_empty_accepted() {
    Position position = Position.create(5, 5);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isTrue();
  }

  @Test
  void assignPosition_firstRow_rejected() {
    Position position = Position.create(0, 5);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_lastRow_rejected() {
    Position position = Position.create(board.getRows() - 1, 5);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_firstColumn_rejected() {
    Position position = Position.create(5, 0);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_secondColumn_rejected() {
    Position position = Position.create(5, 1);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_lastColumn_rejected() {
    Position position = Position.create(5, board.getCols() - 1);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_beforeLastColumn_rejected() {
    Position position = Position.create(5, board.getCols() - 2);
    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);
    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_onGoldStarTool_rejected() {
    Position position = Position.create(5, 5);
    board.setGoldStar(position);

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_goldStarUp_rejected() {
    Position position = Position.create(5, 5);
    board.setGoldStar(position.move(-1, 0));

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_goldStarDown_rejected() {
    Position position = Position.create(5, 5);
    board.setGoldStar(position.move(+1, 0));

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_goldStarLeft_rejected() {
    Position position = Position.create(5, 5);
    board.setGoldStar(position.move(0, -1));

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_goldStarRight_rejected() {
    Position position = Position.create(5, 5);
    board.setGoldStar(position.move(0, +1));

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_threeStarsUp_rejected() {
    Position position = Position.create(5, 5);
    board.setStar(position.move(-1, 0));
    board.setStar(position.move(0, +1));
    board.setStar(position.move(0, -1));

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_threeStarsDown_rejected() {
    Position position = Position.create(5, 5);
    board.setStar(position.move(+1, 0));
    board.setStar(position.move(0, +1));
    board.setStar(position.move(0, -1));

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_threeStarsLeft_rejected() {
    Position position = Position.create(5, 5);
    board.setStar(position.move(0, -1));
    board.setStar(position.move(+1, 0));
    board.setStar(position.move(-1, 0));

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_threeStarsRight_rejected() {
    Position position = Position.create(5, 5);
    board.setStar(position.move(0, +1));
    board.setStar(position.move(+1, 0));
    board.setStar(position.move(-1, 0));

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }

  @Test
  void assignPosition_fourStars_rejected() {
    Position position = Position.create(5, 5);
    board.setStar(position.move(0, -1));
    board.setStar(position.move(0, +1));
    board.setStar(position.move(+1, 0));
    board.setStar(position.move(-1, 0));

    boolean accepted = goldStarBoardAssigner.assignPosition(board, position);

    assertThat(accepted).isFalse();
  }
}
