package com.imoovo.business.usecases;

import com.imoovo.business.database.CalculationHistoryDatabase;
import com.imoovo.business.entity.Distance;
import org.springframework.stereotype.Component;

@Component
public class CalculateDistanceUseCase {
  private CalculationHistoryDatabase calculationHistoryDatabase;
  private Distance distance;

  public CalculateDistanceUseCase withCalculationHistoryDatabase(CalculationHistoryDatabase calculationHistoryDatabase) {
    this.calculationHistoryDatabase = calculationHistoryDatabase;
    return this;
  }

  public CalculateDistanceUseCase forDistance(Distance distance) {
    this.distance = distance;
    return this;
  }

  public Distance run() {
    Distance distance = createDistance();
    return distance;
  }

  private Distance createDistance() {
    return calculationHistoryDatabase.create(distance);
  }
}
