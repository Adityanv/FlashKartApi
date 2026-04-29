package com.FlashKart.FlashKartApi.service;

import com.FlashKart.FlashKartApi.config.CacheNames;
import com.FlashKart.FlashKartApi.dto.ProductRequestDTO;
import com.FlashKart.FlashKartApi.dto.ProductResponseDTO;
import com.FlashKart.FlashKartApi.enums.Category;
import com.FlashKart.FlashKartApi.model.FlashSale;
import com.FlashKart.FlashKartApi.model.Product;
import com.FlashKart.FlashKartApi.repository.FlashSaleRepository;
import com.FlashKart.FlashKartApi.repository.ProductRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FlashSaleRepository flashSaleRepository;

    @Cacheable(value = CacheNames.PRODUCTS, key = "#search + '_' + #category")
    public List<ProductResponseDTO> filterProducts(String search, Category category) {
        List<Product> products = productRepository.findBySearchAndCategory(search, category);
        List<ProductResponseDTO> allProducts = new ArrayList<>();

        for (Product p : products) {
            // Find the sale, but handle the case where it doesn't exist
            FlashSale sale = flashSaleRepository.findByProductId(p.getId());

            // If no sale, price is null (or you can set it to p.getPrice())
            BigDecimal salePrice = (sale != null) ? sale.getSalePrice() : null;

            ProductResponseDTO dto = new ProductResponseDTO(
                    p.getId(), p.getName(), p.getDescription(), p.getCategory(),
                    p.getPrice(), p.getStock(), p.getLowStockThreshold(),
                    p.getEmoji(), salePrice
            );
            allProducts.add(dto);
        }
        return allProducts;
    }

    @CacheEvict(value = CacheNames.PRODUCTS, allEntries = true)
    public Product addNewProduct(ProductRequestDTO newProduct) {
        Product p = new Product(newProduct.getName(), newProduct.getDescription(), newProduct.getCategory(), newProduct.getPrice(), newProduct.getStock(), newProduct.getLowStockThreshold(), newProduct.getEmoji());
        return productRepository.save(p);
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }
}
