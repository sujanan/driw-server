package com.example.driwserver.init;

import com.example.driwserver.product.Product;
import com.example.driwserver.product.ProductRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

  private final ProductRepository productRepository;

  public DataLoader(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Product penguinEars = new Product();
    penguinEars.setName("Penguin-ears");
    penguinEars.setCartonPrice(17500);
    penguinEars.setCartonSize(20);
    Product horseshoe = new Product();
    horseshoe.setName("Horseshoe");
    horseshoe.setCartonPrice(82500);
    horseshoe.setCartonSize(5);
    productRepository.save(penguinEars);
    productRepository.save(horseshoe);
  }
}
