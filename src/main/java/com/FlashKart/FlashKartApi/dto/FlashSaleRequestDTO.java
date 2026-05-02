package com.FlashKart.FlashKartApi.dto;

import java.time.LocalDateTime;

public class FlashSaleRequestDTO {
    private Integer productId;
    private Integer discountPercent;
    private Integer saleStock;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public FlashSaleRequestDTO(Integer productId, Integer discountPercent, Integer saleStock, LocalDateTime startTime, LocalDateTime endTime) {
        this.productId = productId;
        this.discountPercent = discountPercent;
        this.saleStock = saleStock;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public FlashSaleRequestDTO() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
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
}
