package com.dailycodework.productmicroservicc.repositories;


import com.dailycodework.productmicroservicc.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
