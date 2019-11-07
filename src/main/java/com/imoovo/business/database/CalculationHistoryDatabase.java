package com.imoovo.business.database;

import static java.util.Objects.isNull;

import com.imoovo.business.entity.Distance;
import com.imoovo.business.entity.FindDistanceException;
import com.imoovo.business.entity.FindUserException;
import com.imoovo.business.entity.Role;
import com.imoovo.business.entity.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class CalculationHistoryDatabase {
  private static Map<Long, Distance> calculatedDistance = new HashMap<>();
  private static AtomicLong latestDistanceId = new AtomicLong(0);

  CalculationHistoryDatabase() {
    create(calculateDistance());
  }

  public Distance create(Distance distance) {
    if (isNull(distance)) {
      throw new IllegalArgumentException("Job cannot be null");
    }
    Long id = latestDistanceId.incrementAndGet();
    distance.setId(id);
    calculatedDistance.put(id, distance);
    return distance;
  }

  public Distance getById(Long id) {
    if (isNull(id)) {
      throw new FindUserException("Name cannot be null");
    }
    if (!isCalculatedDistanceExist(id)) {
      throw new FindDistanceException(String.format("Not found distance with id: %d", id));
    }
    return calculatedDistance.get(id);
  }

  public List<Distance> getAllDistances(){
    return new ArrayList<>(calculatedDistance.values());
  }

  public boolean isCalculatedDistanceExist(Long id) {
    return calculatedDistance.containsKey(id);
  }

  private Distance calculateDistance() {
    Distance distance = new Distance();
    distance.setDate(LocalDateTime.now());
    distance.setLatitudePoint1(32.9697);
    distance.setLongitudePoint1(-96.80322);
    distance.setLatitudePoint2(29.46786);
    distance.setLongitudePoint2(-98.53506);
    distance.setUser(createUser());
    return distance;
  }

  private User createUser() {
    User user = new User();
    user.setName("aaa");
    user.setRole(Role.ADMIN);
    return user;
  }
}
