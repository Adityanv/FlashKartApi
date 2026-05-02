package com.FlashKart.FlashKartApi.dto;

public class OrderRequestDTO {
    private Integer productId;
    private Integer quantity;
    private String customerName;

    public OrderRequestDTO(Integer id, Integer quantity, String customerName) {
        this.productId = id;
        this.quantity = quantity;
        this.customerName = customerName;
    }

    public OrderRequestDTO() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer id) {
        this.productId = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
