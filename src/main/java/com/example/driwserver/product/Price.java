package com.example.driwserver.product;

public class Price {

  private final Product product;
  private final int quantity;
  private final long amount;

  public Price(Product product, int quantity, long amount) {
    this.product = product;
    this.quantity = quantity;
    this.amount = amount;
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public long getAmount() {
    return amount;
  }
}
