package com.FlashKart.FlashKartApi.event;

public class OrderPlacedEvent {
    private final Integer prductId;
    private final Integer quantity;
    private final Integer flashSaleId;

    public OrderPlacedEvent(Integer prductId, Integer quantity, Integer flashSaleId) {
        this.prductId = prductId;
        this.quantity = quantity;
        this.flashSaleId = flashSaleId;
    }

    public Integer getPrductId() {
        return prductId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getFlashSaleId() {
        return flashSaleId;
    }
}
