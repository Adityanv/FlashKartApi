package com.FlashKart.FlashKartApi.dto;

import com.FlashKart.FlashKartApi.enums.OrderStatus;

public class OrderStatusRequest {
    private OrderStatus status;

    public OrderStatusRequest(OrderStatus status) {
        this.status = status;
    }

    public OrderStatusRequest() {
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
