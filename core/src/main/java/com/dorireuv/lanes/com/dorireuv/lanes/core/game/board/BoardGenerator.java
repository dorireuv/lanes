package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.NumOfPlayers;
import com.dorireuv.lanes.com.dorireuv.lanes.core.Qualifiers.NumOfStars;
import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.BoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.DoublePaymentBoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.FreezeBoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.GoldStarBoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.StarBoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.TrapBoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import java.util.function.Consumer;
import javax.inject.Inject;

public class BoardGenerator {

  private final RandomWrapper randomWrapper;
  private final int numOfPlayers;
  private final int numOfStars;

  @Inject
  public BoardGenerator(
      RandomWrapper randomWrapper, @NumOfPlayers int numOfPlayers, @NumOfStars int numOfStars) {
    this.randomWrapper = randomWrapper;
    this.numOfPlayers = numOfPlayers;
    this.numOfStars = numOfStars;
  }

  public Board generate() {
    Board board = new SimpleBoard();

    assign(board, new StarBoardAssigner(), board::setStar, numOfStars);
    assign(board, new GoldStarBoardAssigner(), board::setGoldStar, getNumOfGoldStars());
    assign(board, new TrapBoardAssigner(), p -> board.getTool(p).setIsTrap(true), getNumOfTraps());
    assign(
        board,
        new FreezeBoardAssigner(),
        p -> board.getTool(p).setIsFreeze(true),
        getNumOfFreezes());
    assign(
        board,
        new DoublePaymentBoardAssigner(),
        p -> board.getTool(p).setIsDoublePayment(true),
        getNumOfDoublePayments());

    return board;
  }

  private int getNumOfGoldStars() {
    return numOfPlayers
            * (int) (Config.getBoardAdditionalRandomNumOfGoldStars() * randomWrapper.nextDouble())
        + Config.getBoardMinNumOfGoldStars();
  }

  private int getNumOfTraps() {
    return getNumOfGoldStars() * Config.getBoardNumOfTrapsToNumOfDoublePaymentsRatio();
  }

  private int getNumOfFreezes() {
    return getNumOfGoldStars() * Config.getBoardNumOfFreezesToNumOfDoublePaymentsRatio();
  }

  private int getNumOfDoublePayments() {
    return Config.getBoardNumOfDoublePayments();
  }

  private void assign(
      ImmutableBoard board,
      BoardAssigner boardAssigner,
      Consumer<Position> assigner,
      int numOfAssignments) {
    int i = 0;
    while (i < numOfAssignments) {
      Position position = randomWrapper.nextPosition(board);
      boolean shouldAssign = boardAssigner.assignPosition(board, position);
      if (shouldAssign) {
        assigner.accept(position);
        i++;
      }
    }
  }
}
