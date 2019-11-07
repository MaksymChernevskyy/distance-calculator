package com.imoovo.business.service;

import com.imoovo.business.database.CalculationHistoryDatabase;
import com.imoovo.business.entity.Distance;
import com.imoovo.business.usecases.CalculateDistanceUseCase;
import com.imoovo.business.usecases.FindDistanceByIdUseCase;
import com.imoovo.business.usecases.GetAllDistancesDataUseCase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {
  private CalculationHistoryDatabase calculationHistoryDatabase;
  private CalculateDistanceUseCase calculateDistanceUseCase;
  private FindDistanceByIdUseCase findDistanceByIdUseCase;
  private GetAllDistancesDataUseCase getAllDistancesDataUseCase;

  @Autowired
  public DistanceService(CalculationHistoryDatabase calculationHistoryDatabase,
                         CalculateDistanceUseCase calculateDistanceUseCase,
                         FindDistanceByIdUseCase findDistanceByIdUseCase,
                         GetAllDistancesDataUseCase getAllDistancesDataUseCase) {
    this.calculationHistoryDatabase = calculationHistoryDatabase;
    this.calculateDistanceUseCase = calculateDistanceUseCase;
    this.findDistanceByIdUseCase = findDistanceByIdUseCase;
    this.getAllDistancesDataUseCase = getAllDistancesDataUseCase;
  }

  public Double calculate(Distance distance) {
    double calculation = org.apache.lucene.util.SloppyMath.haversinMeters(distance.getLatitudePoint1(), distance.getLongitudePoint1(),
        (distance.getLatitudePoint1()), distance.getLongitudePoint2());
    return calculation / 1000;
  }

  public Distance savePoints(Distance distance) {
    return calculateDistanceUseCase
        .withCalculationHistoryDatabase(calculationHistoryDatabase)
        .forDistance(distance)
        .run();
  }

  public Distance getDistanceById(Long id) {
    return findDistanceByIdUseCase
        .withCalculationHistoryDatabase(calculationHistoryDatabase)
        .forDistanceId(id)
        .run();
  }

  public List<Distance> getAllDistances() {
    return getAllDistancesDataUseCase
        .withCalculationHistoryDatabase(calculationHistoryDatabase)
        .run();
  }
}
