package com.imoovo.business.database;

import static java.util.Objects.isNull;

import com.imoovo.business.entity.*;

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

    public List<Distance> getAllDistances() {
        return new ArrayList<>(calculatedDistance.values());
    }

    public boolean isCalculatedDistanceExist(Long id) {
        return calculatedDistance.containsKey(id);
    }

    private Distance calculateDistance() {
        Distance distance = new Distance();
        distance.setDate(LocalDateTime.now());
        distance.setLatLong1(createLatLong1());
        distance.setLatLong2(createLatLong2());
        distance.setUser(createUser());
        return distance;
    }

    private LatLong createLatLong1() {
        LatLong latLong = new LatLong();
        latLong.setLatitudePoint(32.9697);
        latLong.setLongitudePoint(-96.80322);
        return latLong;
    }

    private LatLong createLatLong2() {
        LatLong latLong = new LatLong();
        latLong.setLatitudePoint(29.46786);
        latLong.setLongitudePoint(-98.53506);
        return latLong;
    }

    private User createUser() {
        User user = new User();
        user.setName("aaa");
        user.setRole(Role.ADMIN);
        return user;
    }
}
