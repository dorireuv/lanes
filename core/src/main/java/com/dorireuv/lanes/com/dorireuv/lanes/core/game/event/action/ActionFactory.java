package com.dorireuv.lanes.com.dorireuv.lanes.core.game.event.action;

import com.dorireuv.lanes.com.dorireuv.lanes.core.Lanes;
import com.dorireuv.lanes.com.dorireuv.lanes.core.data.event.GalacticBombEventDefinition;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.board.Position;
import com.dorireuv.lanes.com.dorireuv.lanes.core.game.company.Company;

public class ActionFactory {

  private final Lanes lanes;

  public ActionFactory(Lanes lanes) {
    this.lanes = lanes;
  }

  public Action createBankCashMoneyInterestAction() {
    return new BankCashMoneyInterestAction(lanes.getGame().getBank());
  }

  public Action createBonusPaymentAction() {
    return new BonusPaymentAction(
        lanes.getClientEventSubscriberGroup(), lanes.getGame().getBank(), lanes.getCurrentPlayer());
  }

  public Action createCompanyUpdateAction(Company company) {
    return new CompanyUpdateAction(
        lanes.getClientEventSubscriberGroup(),
        lanes.getBoard(),
        lanes.getCurrentMove(),
        company,
        lanes.getGame().getPlayers());
  }

  public Action createCreateCompanyAction(Company company) {
    return new CreateCompanyAction(
        lanes.getClientEventSubscriberGroup(),
        lanes.getCurrentPlayer(),
        lanes.getBoard(),
        lanes.getCurrentMove(),
        company);
  }

  public Action createDoublePaymentAction() {
    return new DoublePaymentAction(lanes.getClientEventSubscriberGroup(), lanes.getCurrentPlayer());
  }

  public Action createFreezeAction() {
    return new FreezeAction(lanes.getClientEventSubscriberGroup(), lanes.getTurnIterator());
  }

  public Action createGalacticBombAction(
      Company company, GalacticBombEventDefinition galacticBombEventDefinition) {
    return new GalacticBombAction(
        lanes.getClientEventSubscriberGroup(), company, galacticBombEventDefinition);
  }

  public Action createGoldStarDisappearAction(Position position) {
    return new GoldStarDisappearAction(
        lanes.getClientEventSubscriberGroup(), lanes.getBoard(), position);
  }

  public Action createGrowCompanyAction(Company company) {
    return new GrowCompanyAction(
        lanes.getClientEventSubscriberGroup(), lanes.getBoard(), lanes.getCurrentMove(), company);
  }

  public Action createHalfTrapAction() {
    return new HalfTrapAction(
        lanes.getClientEventSubscriberGroup(),
        lanes.getTurnIterator(),
        lanes.getGame().getBank(),
        lanes.getCurrentPlayer());
  }

  public Action createHitAction() {
    return new HitAction(
        lanes.getClientEventSubscriberGroup(), lanes.getBoard(), lanes.getCurrentMove());
  }

  public Action createMergeCompanyAction(Company mergedIntoCompany, Company mergedCompany) {
    return new MergeCompanyAction(
        lanes.getClientEventSubscriberGroup(),
        lanes.getGame().getPlayers(),
        lanes.getGame().getBoard(),
        lanes.getCurrentMove(),
        mergedIntoCompany,
        mergedCompany);
  }

  public Action createNullAction() {
    return new NullAction();
  }

  public Action createPlayerCashMoneyInterestAction() {
    return new PlayerCashMoneyInterestAction(
        lanes.getCurrentPlayer(), lanes.getGame().getCompanies().values());
  }

  public Action createTrapAction() {
    return new TrapAction(
        lanes.getClientEventSubscriberGroup(),
        lanes.getTurnIterator(),
        lanes.getGame().getBank(),
        lanes.getCurrentPlayer());
  }
}
