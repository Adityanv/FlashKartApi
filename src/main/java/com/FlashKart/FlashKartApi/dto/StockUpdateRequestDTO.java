package com.FlashKart.FlashKartApi.dto;

public class StockUpdateRequestDTO {
    private Integer quantity;

    public StockUpdateRequestDTO(Integer quantity) {
        this.quantity = quantity;
    }

    public StockUpdateRequestDTO() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
