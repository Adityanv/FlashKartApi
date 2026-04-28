package com.FlashKart.FlashKartApi.model;

import com.FlashKart.FlashKartApi.enums.SaleStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "flash_sales")
public class FlashSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    private Integer discountPercent;
    private BigDecimal salePrice;
    private Integer saleStock;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private SaleStatus status;
    private LocalDateTime createdAt;

    public FlashSale() {
    }

    public FlashSale(Product product, Integer discountPercent, BigDecimal salePrice, Integer saleStock, LocalDateTime saleTime, LocalDateTime endTime, SaleStatus status, LocalDateTime createdAt) {
        this.product = product;
        this.discountPercent = discountPercent;
        this.salePrice = salePrice;
        this.saleStock = saleStock;
        this.startTime = saleTime;
        this.endTime = endTime;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Integer getSaleStock() {
        return saleStock;
    }

    public void setSaleStock(Integer saleStock) {
        this.saleStock = saleStock;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
