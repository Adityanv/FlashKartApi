package com.FlashKart.FlashKartApi.dto;

import com.FlashKart.FlashKartApi.enums.Category;

import java.math.BigDecimal;

public class ProductResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private Category category;
    private BigDecimal price;
    private Integer stock;
    private Integer lowStockThreshold;
    private String emoji;
    private BigDecimal salePrice;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Integer id, String name, String description, Category category, BigDecimal price, Integer stock, Integer lowStockThreshold, String emoji, BigDecimal salePrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.lowStockThreshold = lowStockThreshold;
        this.emoji = emoji;
        this.salePrice = salePrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setLowStockThreshold(Integer lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}
