package com.imoovo.business.entity;

import java.time.LocalDateTime;

public class Distance {
  private Long id;
  private Double latitudePoint1;
  private Double longitudePoint1;
  private Double latitudePoint2;
  private Double longitudePoint2;
  private User user;
  private LocalDateTime date;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Double getLatitudePoint1() {
    return latitudePoint1;
  }

  public void setLatitudePoint1(Double latitudePoint1) {
    this.latitudePoint1 = latitudePoint1;
  }

  public Double getLongitudePoint1() {
    return longitudePoint1;
  }

  public void setLongitudePoint1(Double longitudePoint1) {
    this.longitudePoint1 = longitudePoint1;
  }

  public Double getLatitudePoint2() {
    return latitudePoint2;
  }

  public void setLatitudePoint2(Double latitudePoint2) {
    this.latitudePoint2 = latitudePoint2;
  }

  public Double getLongitudePoint2() {
    return longitudePoint2;
  }

  public void setLongitudePoint2(Double longitudePoint2) {
    this.longitudePoint2 = longitudePoint2;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }
}

