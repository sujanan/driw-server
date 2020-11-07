package com.example.driwserver.product;

import com.example.driwserver.error.ErrorResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;
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
    return service.getPriceCollection(
        entityManager.find(Product.class, id),
        (int) (pageable.getOffset() + 1),
        ((pageable.getPageNumber() + 1) * pageable.getPageSize()) + 1);
  }

  @ExceptionHandler({ConstraintViolationException.class, MethodArgumentTypeMismatchException.class})
  @ResponseStatus(HttpStatus.OK)
  public ErrorResponse handleConstraintViolationException(Exception e) {
    return ErrorResponse.forError(HttpStatus.BAD_REQUEST, e.getMessage());
  }
}
