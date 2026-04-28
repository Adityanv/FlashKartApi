package com.FlashKart.FlashKartApi.repository;

import com.FlashKart.FlashKartApi.enums.Category;
import com.FlashKart.FlashKartApi.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    //Filter by Category
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategory(Category category);

    //Search by name
    List<Product> findByNameContainingIgnoreCase(String search);

    //Low Threshold alert
    @Query("SELECT p FROM Product p WHERE p.stock < :threshold")
    List<Product> findStockLessThan(Integer threshold);
}
