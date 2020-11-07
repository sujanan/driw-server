package com.example.driwserver.product;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PriceCalculationService {

  public Price getPrice(Product product, int quantity) {
    return new DefaultPriceCalculator(product).calculate(quantity);
  }

  public Collection<Price> getPriceCollection(Product product, int start, int end) {
    PriceCalculator calculator = new DefaultPriceCalculator(product);
    return IntStream.range(start, end)
        .boxed()
        .map(calculator::calculate)
        .collect(Collectors.toList());
  }
}
