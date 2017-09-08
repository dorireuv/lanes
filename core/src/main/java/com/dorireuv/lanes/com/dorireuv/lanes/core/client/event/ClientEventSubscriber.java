package com.dorireuv.lanes.com.dorireuv.lanes.core.client.event;

public interface ClientEventSubscriber {

  void onBankCashMoneyChange(BankCashMoneyChangeEvent bankCashMoneyChangeEvent);

  void onBoardChange(BoardChangeEvent boardChangeEvent);

  void onBonusPayment(BonusPaymentEvent bonusPaymentEvent);

  void onCompanyTopHolderChange(CompanyTopHolderChangeEvent companyTopHolderChangeEvent);

  void onCreateCompany(CreateCompanyEvent createCompanyEvent);

  void onDoublePayment(DoublePaymentEvent doublePaymentEvent);

  void onFreeze(FreezeEvent freezeEvent);

  void onGalacticBomb(GalacticBombEvent galacticBombEvent);

  void onGoldStarDisappear(GoldStarDisappearEvent goldStarDisappearEvent);

  void onGrowCompany(GrowCompanyEvent growCompanyEvent);

  void onHalfTrap(HalfTrapEvent halfTrapEvent);

  void onHit(HitEvent hitEvent);

  void onMergeCompany(MergeCompanyEvent mergeCompanyEvent);

  void onPlayerCashMoneyChange(PlayerCashMoneyChangeEvent playerCashMoneyChangeEvent);

  void onPlayerNetValueChange(PlayerNetValueChangeEvent playerNetValueChangeEvent);

  void onTrap(TrapEvent trapEvent);
}
