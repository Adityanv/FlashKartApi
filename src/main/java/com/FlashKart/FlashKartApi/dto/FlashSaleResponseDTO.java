package com.FlashKart.FlashKartApi.dto;

import com.FlashKart.FlashKartApi.enums.Category;
import com.FlashKart.FlashKartApi.enums.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FlashSaleResponseDTO {
    private Integer id;
    private Integer productId;
    private String productName;
    private String productEmoji;
    private Category productCategory;
    private BigDecimal originalPrice;
    private Integer discountPercent;
    private BigDecimal salePrice;
    private Integer stock;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SaleStatus status;

    public FlashSaleResponseDTO() {
    }

    public FlashSaleResponseDTO(Integer id, Integer productId, String productName, String productEmoji, Category productCategory, BigDecimal originalPrice, Integer discountPercent, BigDecimal salePrice, Integer stock, LocalDateTime startTime, LocalDateTime endTime, SaleStatus status) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productEmoji = productEmoji;
        this.productCategory = productCategory;
        this.originalPrice = originalPrice;
        this.discountPercent = discountPercent;
        this.salePrice = salePrice;
        this.stock = stock;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductEmoji() {
        return productEmoji;
    }

    public void setProductEmoji(String productEmoji) {
        this.productEmoji = productEmoji;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }
}
