package com.imoovo.business.entity;

import java.time.LocalDateTime;

public class Distance {
  private Long id;
  private LatLong latLong1;
  private LatLong latLong2;
  private User user;
  private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LatLong getLatLong1() {
        return latLong1;
    }

    public void setLatLong1(LatLong latLong1) {
        this.latLong1 = latLong1;
    }

    public LatLong getLatLong2() {
        return latLong2;
    }

    public void setLatLong2(LatLong latLong2) {
        this.latLong2 = latLong2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "id=" + id +
                ", latLong1=" + latLong1 +
                ", latLong2=" + latLong2 +
                ", user=" + user +
                ", date=" + date +
                '}' ;
    }
}

