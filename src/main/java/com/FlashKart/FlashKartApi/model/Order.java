package com.FlashKart.FlashKartApi.model;

import com.FlashKart.FlashKartApi.enums.SaleStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @Nullable
    private FlashSale flashSale;
    private String customerName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    private SaleStatus status;
    private LocalDateTime createdAt;

    public Order() {
    }

    public Order(Product product, @Nullable FlashSale flashSale, String customerName, Integer quantity, BigDecimal unitPrice, BigDecimal totalPrice, SaleStatus status, LocalDateTime createdAt) {
        this.product = product;
        this.flashSale = flashSale;
        this.customerName = customerName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
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

    @Nullable
    public FlashSale getFlashSale() {
        return flashSale;
    }

    public void setFlashSale(@Nullable FlashSale flashSale) {
        this.flashSale = flashSale;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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
