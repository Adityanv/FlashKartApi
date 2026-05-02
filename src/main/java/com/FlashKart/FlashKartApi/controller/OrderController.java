package com.FlashKart.FlashKartApi.controller;

import com.FlashKart.FlashKartApi.dto.OrderRequestDTO;
import com.FlashKart.FlashKartApi.dto.OrderResponseDTO;
import com.FlashKart.FlashKartApi.dto.OrderStatusRequest;
import com.FlashKart.FlashKartApi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> placeNewOrder(@RequestBody OrderRequestDTO newOrderRequest){
        orderService.placeOrder(newOrderRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> displayAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Integer id, @RequestBody OrderStatusRequest newStatus){
        orderService.updateStatus(id, newStatus);
        return ResponseEntity.noContent().build();
    }
}
