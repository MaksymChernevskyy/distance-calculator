package com.imoovo.business.usecases;

import com.imoovo.business.database.CalculationHistoryDatabase;
import com.imoovo.business.entity.Distance;
import org.springframework.stereotype.Component;

@Component
public class FindDistanceByIdUseCase {
  private CalculationHistoryDatabase calculationHistoryDatabase;
  private Long id;

  public FindDistanceByIdUseCase withCalculationHistoryDatabase(CalculationHistoryDatabase calculationHistoryDatabase) {
    this.calculationHistoryDatabase = calculationHistoryDatabase;
    return this;
  }

  public FindDistanceByIdUseCase forDistanceId(Long id) {
    this.id = id;
    return this;
  }

  public Distance run() {
    return calculationHistoryDatabase.getById(id);
  }
}
