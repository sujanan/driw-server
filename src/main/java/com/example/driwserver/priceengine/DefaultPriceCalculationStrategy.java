package com.example.driwserver.priceengine;

public class DefaultPriceCalculationStrategy implements PriceCalculationStrategy {
  private final double unitIncrease;
  private final double cartonDiscount;
  private final int cartonDiscountMinBound;

  public static class Builder {
    private double unitIncrease = 0.3;
    private double cartonDiscount = 0.1;
    private int cartonDiscountMinBound = 3;

    public Builder unitIncrease(double unitIncrease) {
      this.unitIncrease = Math.min(Math.max(unitIncrease, 0.0), 1.0);
      return this;
    }

    public Builder cartonDiscount(double cartonDiscount) {
      this.cartonDiscount = Math.min(Math.max(cartonDiscount, 0.0), 1.0);
      return this;
    }

    public Builder cartonDiscountMinBound(int cartonDiscountMinBound) {
      this.cartonDiscountMinBound = cartonDiscountMinBound;
      return this;
    }

    public DefaultPriceCalculationStrategy build() {
      return new DefaultPriceCalculationStrategy(this);
    }
  }

  private DefaultPriceCalculationStrategy(Builder builder) {
    unitIncrease = builder.unitIncrease;
    cartonDiscount = builder.cartonDiscount;
    cartonDiscountMinBound = builder.cartonDiscountMinBound;
  }

  @Override
  public long calculate(long cartonPrice, int cartonSize, int quantity) {
    if (cartonPrice < 0 || quantity < 0) {
      throw new IllegalArgumentException("Carton price and quantity must be non-negative values");
    }
    if (cartonSize <= 0) {
      throw new IllegalArgumentException("Units per carton must be a positive integer");
    }
    int cartons = quantity / cartonSize;
    int units = quantity % cartonSize;
    double newCartonPrice =
        (cartons >= cartonDiscountMinBound) ? (1 - cartonDiscount) * cartonPrice : cartonPrice;
    double newUnitPrice = (newCartonPrice * (1 + unitIncrease)) / cartonSize;
    return Math.round(newCartonPrice * cartons + newUnitPrice * units);
  }
}
