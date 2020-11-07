package com.example.driwserver.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "products")
public class Product {

  @Id @GeneratedValue private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private long cartonPrice;

  private int cartonSize;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getCartonPrice() {
    return cartonPrice;
  }

  public void setCartonPrice(long cartonPrice) {
    this.cartonPrice = cartonPrice;
  }

  public int getCartonSize() {
    return cartonSize;
  }

  public void setCartonSize(int cartonSize) {
    this.cartonSize = cartonSize;
  }
}
