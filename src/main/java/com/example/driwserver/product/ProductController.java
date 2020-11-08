package com.example.driwserver.product;

import com.example.driwserver.error.ErrorResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection;

@Validated
@RestController
public class ProductController {

  private final ProductRepository repository;
  private final PriceCalculationService service;
  private final EntityManager entityManager;

  public ProductController(
      ProductRepository repository, PriceCalculationService service, EntityManager entityManager) {
    this.repository = repository;
    this.service = service;
    this.entityManager = entityManager;
  }

  @GetMapping("/api/products")
  public Collection<Product> getProducts(@PageableDefault(value = 50) Pageable pageable) {
    return repository.findAll(pageable).getContent();
  }

  @GetMapping("/api/products/{id}/prices")
  public Price getPriceOfProducts(
      @PathVariable Long id,
      @RequestParam(required = false, defaultValue = "1") @Min(0) @Max(10000) int quantity) {
    return service.getPrice(entityManager.find(Product.class, id), quantity);
  }

  @GetMapping("/api/products/{id}/prices/collection")
  public Collection<Price> getPriceCollectionOfProducts(
      @PathVariable Long id, @PageableDefault(value = 50) Pageable pageable) {
    int start = (int) (pageable.getOffset() + 1);
    int end = ((pageable.getPageNumber() + 1) * pageable.getPageSize()) + 1;
    if (end > 10000) {
      throw new IllegalArgumentException("Quantity must be less than or equal to 10000");
    }
    return service.getPriceCollection(entityManager.find(Product.class, id), start, end);
  }

  @ExceptionHandler({Exception.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleErrors(Exception e) {
    return ErrorResponse.forError(HttpStatus.BAD_REQUEST, e.getMessage());
  }
}
