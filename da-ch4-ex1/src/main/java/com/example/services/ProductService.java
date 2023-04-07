package com.example.services;

import com.example.model.dtos.TotalCostResponse;
import com.example.model.entities.Product;
import com.example.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.*;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public TotalCostResponse getTotalCosts() {
    TotalCostResponse response = new TotalCostResponse();
    try {
      Collection<Product> products = productRepository.findAll();

      Map<String, BigDecimal> costs = products.stream()
          .collect(Collectors.toMap(
              Product::getName,
              p -> p.getPrice().multiply(new BigDecimal(p.getQuantity()))));

      response.setTotalCosts(costs);
    } catch (Exception e) {
      e.printStackTrace(); // do nothing
    }

    return response;
  }

}
