package com.FlashKart.FlashKartApi.repository;

import com.FlashKart.FlashKartApi.enums.Category;
import com.FlashKart.FlashKartApi.model.Product;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    //Filter by Category
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategory(Category category);

    //Search by name
    List<Product> findByNameContainingIgnoreCase(String search);

    //Low Threshold alert
    @Query("SELECT p FROM Product p WHERE p.stock < :threshold")
    List<Product> findStockLessThan(Integer threshold);

    @Query("SELECT p FROM Product p WHERE " +
            "(:category IS NULL OR p.category = :category) AND " +
            "(:search IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<Product> findBySearchAndCategory(@Param("search") String search, @Param("category") Category category);

    @Query("SELECT p FROM Product p WHERE p.stock < p.lowStockThreshold")
    List<Product> getAllLowStockProducts();
}
