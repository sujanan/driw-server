package com.example.driwserver.product;

import com.example.driwserver.priceengine.DefaultPriceCalculationStrategy;
import com.example.driwserver.priceengine.PriceCalculationStrategy;

public class DefaultPriceCalculator extends PriceCalculator {

  protected DefaultPriceCalculator(Product product, PriceCalculationStrategy calculationStrategy) {
    super(product, calculationStrategy);
  }

  public DefaultPriceCalculator(Product product) {
    super(product, new DefaultPriceCalculationStrategy.Builder().build());
  }
}
