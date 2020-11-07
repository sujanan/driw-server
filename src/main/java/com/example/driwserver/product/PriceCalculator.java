package com.example.driwserver.product;

import com.example.driwserver.priceengine.PriceCalculationStrategy;

public abstract class PriceCalculator {
  private final Product product;
  private final PriceCalculationStrategy calculationStrategy;

  protected PriceCalculator(Product product, PriceCalculationStrategy calculationStrategy) {
    this.product = product;
    this.calculationStrategy = calculationStrategy;
  }

  public Price calculate(int quantity) {
    long amount =
        calculationStrategy.calculate(product.getCartonPrice(), product.getCartonSize(), quantity);
    return new Price(product, quantity, amount);
  }
}
