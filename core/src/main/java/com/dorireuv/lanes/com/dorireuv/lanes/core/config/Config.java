package com.dorireuv.lanes.com.dorireuv.lanes.core.config;

import java.io.IOException;
import java.util.Properties;

public final class Config {

  private static final Properties properties = new Properties();

  static {
    try {
      properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
    } catch (IOException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public static int getBoardRows() {
    return Integer.parseInt(properties.getProperty("board_rows"));
  }

  public static int getBoardCols() {
    return Integer.parseInt(properties.getProperty("board_cols"));
  }

  public static double getBankCashInterest() {
    return Double.parseDouble(properties.getProperty("bank_cash_interest"));
  }

  public static int getCompanySplitValue() {
    return Integer.parseInt(properties.getProperty("company_split_value"));
  }

  public static int getCompanyGoldStarValue() {
    return Integer.parseInt(properties.getProperty("company_gold_star_value"));
  }

  public static int getCompanyStarValue() {
    return Integer.parseInt(properties.getProperty("company_star_value"));
  }

  public static int getCompanyHitValue() {
    return Integer.parseInt(properties.getProperty("company_hit_value"));
  }

  public static double getBonusPaymentChance() {
    return Double.parseDouble(properties.getProperty("bonus_payment_chance"));
  }

  public static double getBonusPaymentPercentage() {
    return Double.parseDouble(properties.getProperty("bonus_payment_percentage"));
  }

  public static double getGalacticBombChance() {
    return Double.parseDouble(properties.getProperty("galactic_bomb_chance"));
  }

  public static int getGalacticBombNumOfCompanySearchRetries() {
    return Integer.parseInt(properties.getProperty("galactic_bomb_num_of_company_search_retries"));
  }

  public static int getGoldStarDisappearNumOfRetries() {
    return Integer.parseInt(properties.getProperty("gold_star_disappear_num_of_retries"));
  }

  public static double getHalfTrapChance() {
    return Double.parseDouble(properties.getProperty("half_trap_chance"));
  }

  public static int getPlayerGameStartCashMoney() {
    return Integer.parseInt(properties.getProperty("player_game_start_cash_money"));
  }

  public static int getBankGameStartCashMoney() {
    return Integer.parseInt(properties.getProperty("bank_game_start_cash_money"));
  }

  public static int getCompanyNumOfStocksOnCreation() {
    return Integer.parseInt(properties.getProperty("company_num_of_stocks_on_creation"));
  }

  public static double getMergeBonusPercentage() {
    return Double.parseDouble(properties.getProperty("merge_bonus_percentage"));
  }

  public static double getSellCommission() {
    return Double.parseDouble(properties.getProperty("sell_commission"));
  }

  public static int getNumOfMoveOptions() {
    return Integer.parseInt(properties.getProperty("num_of_move_options"));
  }

  public static int getBoardNumOfDoublePayments() {
    return Integer.parseInt(properties.getProperty("board_num_of_double_payments"));
  }

  public static int getBoardNumOfFreezesToNumOfDoublePaymentsRatio() {
    return Integer.parseInt(
        properties.getProperty("board_num_of_freezes_to_num_of_double_payments_ratio"));
  }

  public static int getBoardNumOfTrapsToNumOfDoublePaymentsRatio() {
    return Integer.parseInt(
        properties.getProperty("board_num_of_traps_to_num_of_double_payments_ratio"));
  }

  public static int getBoardMinNumOfGoldStars() {
    return Integer.parseInt(properties.getProperty("board_min_num_of_gold_stars"));
  }

  public static double getBoardAdditionalRandomNumOfGoldStars() {
    return Double.parseDouble(properties.getProperty("board_additional_random_num_of_gold_stars"));
  }

  public static int getMaxNumOfPlayers() {
    return Integer.parseInt(properties.getProperty("max_num_of_players"));
  }
}
