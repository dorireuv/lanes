package com.dorireuv.lanes.com.dorireuv.lanes.core.game.board;

import com.dorireuv.lanes.com.dorireuv.lanes.core.config.Config;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.Assigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.AssignerGroup;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.RandomAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.RepeatedAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board.BoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board.DoublePaymentBoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board.FreezeBoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board.GoldStarBoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board.StarBoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.assigner.board.TrapBoardAssigner;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.RandomWrapper;
import com.dorireuv.lanes.com.dorireuv.lanes.core.util.random.SimpleRandomWrapper;

public class BoardBuilder {

  private RandomWrapper randomWrapper;
  private Board board;
  private int numOfStars;
  private int numOfPlayers;
  private int numOfGoldStars;
  private int numOfTraps;
  private int numOfFreezes;
  private int numOfDoublePayments;

  public Board buildDefault(int numOfStars, int numOfPlayers, long randomSeed) {
    init(numOfStars, numOfPlayers, randomSeed);

    setNumOfGoldStars();
    setNumOfTraps();
    setNumOfFreezes();
    setNumOfDoublePayments();
    buildBoard();

    return board;
  }

  private void init(int numOfStars, int numOfPlayers, long randomSeed) {
    this.numOfStars = numOfStars;
    this.numOfPlayers = numOfPlayers;
    this.randomWrapper = new SimpleRandomWrapper(randomSeed);
  }

  private void setNumOfGoldStars() {
    numOfGoldStars =
        numOfPlayers
            * (int)
            (Config.getBoardAdditionalRandomNumOfGoldStars() * randomWrapper.nextDouble())
            + Config.getBoardMinNumOfGoldStars();
  }

  private void setNumOfTraps() {
    numOfTraps = numOfGoldStars * Config.getBoardNumOfTrapsToNumOfDoublePaymentsRatio();
  }

  private void setNumOfFreezes() {
    numOfFreezes = numOfGoldStars * Config.getBoardNumOfFreezesToNumOfDoublePaymentsRatio();
  }

  private void setNumOfDoublePayments() {
    numOfDoublePayments = Config.getBoardNumOfDoublePayments();
  }

  private Assigner createRepeatedRandomAssigner(BoardAssigner boardAssigner, int numOfRepeats) {
    return new RepeatedAssigner(
        new RandomAssigner(randomWrapper, boardAssigner, board), numOfRepeats);
  }

  private void buildBoard() {
    board = new SimpleBoard();
    AssignerGroup assigner =
        new AssignerGroup(
            createRepeatedRandomAssigner(new StarBoardAssigner(), numOfStars),
            createRepeatedRandomAssigner(new GoldStarBoardAssigner(), numOfGoldStars),
            createRepeatedRandomAssigner(new TrapBoardAssigner(), numOfTraps),
            createRepeatedRandomAssigner(new FreezeBoardAssigner(), numOfFreezes),
            createRepeatedRandomAssigner(new DoublePaymentBoardAssigner(), numOfDoublePayments));
    assigner.assign();
  }
}
