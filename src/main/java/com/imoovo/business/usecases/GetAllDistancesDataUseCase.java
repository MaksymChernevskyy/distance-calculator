package com.imoovo.business.usecases;

import com.imoovo.business.database.CalculationHistoryDatabase;
import com.imoovo.business.entity.Distance;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetAllDistancesDataUseCase {
  private CalculationHistoryDatabase calculationHistoryDatabase;

  public GetAllDistancesDataUseCase withCalculationHistoryDatabase(CalculationHistoryDatabase calculationHistoryDatabase) {
    this.calculationHistoryDatabase = calculationHistoryDatabase;
    return this;
  }

  public List<Distance> run() {
    return calculationHistoryDatabase.getAllDistances();
  }
}
