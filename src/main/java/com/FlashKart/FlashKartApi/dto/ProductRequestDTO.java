package com.FlashKart.FlashKartApi.dto;

import com.FlashKart.FlashKartApi.enums.Category;

import java.math.BigDecimal;

public class ProductRequestDTO {
    private String name;
    private String description;
    private String emoji;
    private Category category;
    private BigDecimal price;
    private Integer stock;
    private Integer lowStockThreshold;

    public ProductRequestDTO(String name, String description, String emoji, Category category, BigDecimal price, Integer stock, Integer lowStockThreshold) {
        this.name = name;
        this.description = description;
        this.emoji = emoji;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.lowStockThreshold = lowStockThreshold;
    }

    public ProductRequestDTO() {
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

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
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
}
