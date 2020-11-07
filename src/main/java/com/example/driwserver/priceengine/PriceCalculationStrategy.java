package com.example.driwserver.priceengine;

public interface PriceCalculationStrategy {

  long calculate(long cartonPrice, int cartonSize, int quantity);
}
