package com.FlashKart.FlashKartApi.dto;

public class StatsResponseDTO {
    private long totalProducts;
    private Integer activeSales;
    private long ordersToday;
    private Integer lowStockCount;

    public StatsResponseDTO() {
    }

    public StatsResponseDTO(long totalProducts, Integer activeSales, long ordersToday, Integer lowStockCount) {
        this.totalProducts = totalProducts;
        this.activeSales = activeSales;
        this.ordersToday = ordersToday;
        this.lowStockCount = lowStockCount;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Integer getActiveSales() {
        return activeSales;
    }

    public void setActiveSales(Integer activeSales) {
        this.activeSales = activeSales;
    }

    public long getOrdersToday() {
        return ordersToday;
    }

    public void setOrdersToday(long ordersToday) {
        this.ordersToday = ordersToday;
    }

    public Integer getLowStockCount() {
        return lowStockCount;
    }

    public void setLowStockCount(Integer lowStockCount) {
        this.lowStockCount = lowStockCount;
    }
}
