package com.example.driwserver;

import com.example.driwserver.priceengine.DefaultPriceCalculationStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DefaultPriceCalculationStrategyTests {
  private final DefaultPriceCalculationStrategy strategy =
      new DefaultPriceCalculationStrategy.Builder().build();

  @Test
  void calculatePriceForPriceLessThanZeroCartonTest() {
    assertThrows(IllegalArgumentException.class, () -> strategy.calculate(-1, 20, 5));
  }

  @Test
  void calculatePriceForCartonSizeLessThanOrEqualZeroTest() {
    assertThrows(IllegalArgumentException.class, () -> strategy.calculate(17500, 0, 5));
    assertThrows(IllegalArgumentException.class, () -> strategy.calculate(17500, -1, 5));
  }

  @Test
  void calculateCostForQuantityLessThanZeroTest() {
    assertThrows(IllegalArgumentException.class, () -> strategy.calculate(17500, 20, -1));
  }

  @Test
  void calculatePriceForQuantityOrAndCartonPriceZeroTest() {
    assertEquals(0, strategy.calculate(0, 20, 5));
    assertEquals(0, strategy.calculate(17500, 20, 0));
    assertEquals(0, strategy.calculate(0, 20, 0));
  }

  @Test
  void calculatePriceForQuantityEqualOneTest() {
    assertEquals(0, strategy.calculate(0, 20, 1));
    assertEquals(1138, strategy.calculate(17500, 20, 1));
  }

  @Test
  void calculatePriceForQuantityEqualCartonSizeTest() {
    assertEquals(17500, strategy.calculate(17500, 20, 20));
  }

  @Test
  void calculatePriceForQuantityGreaterThanCartonSizeTest() {
    assertEquals(23188, strategy.calculate(17500, 20, 25));
    assertEquals(47250, strategy.calculate(17500, 20, 60));
  }
}
