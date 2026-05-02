package com.FlashKart.FlashKartApi.service;

import com.FlashKart.FlashKartApi.dto.OrderRequestDTO;
import com.FlashKart.FlashKartApi.dto.OrderResponseDTO;
import com.FlashKart.FlashKartApi.dto.OrderStatusRequest;
import com.FlashKart.FlashKartApi.enums.OrderStatus;
import com.FlashKart.FlashKartApi.enums.SaleStatus;
import com.FlashKart.FlashKartApi.event.OrderPlacedEvent;
import com.FlashKart.FlashKartApi.model.FlashSale;
import com.FlashKart.FlashKartApi.model.Order;
import com.FlashKart.FlashKartApi.model.Product;
import com.FlashKart.FlashKartApi.repository.FlashSaleRepository;
import com.FlashKart.FlashKartApi.repository.OrderRepository;
import com.FlashKart.FlashKartApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FlashSaleRepository flashSaleRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public void placeOrder(OrderRequestDTO newOrderRequest) {
        // Prevent NullPointer or IllegalArgumentException if the DTO is empty or misaligned
        if (newOrderRequest == null || newOrderRequest.getProductId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product ID must not be null");
        }
        Integer productId = newOrderRequest.getProductId();
        Product p = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
        Optional<FlashSale> activeSale = flashSaleRepository.findByProductIdAndStatus(productId, SaleStatus.ACTIVE);

        BigDecimal unitPrice;
        int availableStock;
        FlashSale flashSale = null;

        if (activeSale.isPresent()) {
            flashSale = activeSale.get();
            unitPrice = flashSale.getSalePrice();
            availableStock = flashSale.getSaleStock();
        } else {
            unitPrice = p.getPrice();
            availableStock = p.getStock();
        }
        if (newOrderRequest.getQuantity() == null || newOrderRequest.getQuantity() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid order quantity");
        }

        if (newOrderRequest.getQuantity() > availableStock) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Insufficient stock. Only " + availableStock + " available.");
        }
        Order order = new Order(
                p,
                flashSale,
                newOrderRequest.getCustomerName(),
                newOrderRequest.getQuantity(),
                unitPrice,
                unitPrice.multiply(BigDecimal.valueOf(newOrderRequest.getQuantity())),
                OrderStatus.PENDING,
                LocalDateTime.now()
        );
        orderRepository.save(order);
        Integer flashSaleId = (flashSale == null) ? null : flashSale.getId();
        eventPublisher.publishEvent(new OrderPlacedEvent(p.getId(), newOrderRequest.getQuantity(), flashSaleId));
    }

    @Transactional
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        List<OrderResponseDTO> allOrders = new ArrayList<>();

        for(Order order : orderList){
            Product product = order.getProduct();
            FlashSale flashSale = order.getFlashSale();
            Integer flashSaleId = flashSale == null ? null : flashSale.getId();
            OrderResponseDTO newOrderResponse = new OrderResponseDTO(
                    order.getId(),
                    product.getId(),
                    product.getName(),
                    order.getCustomerName(),
                    order.getQuantity(),
                    order.getUnitPrice(),
                    order.getTotalPrice(),
                    order.getStatus(),
                    flashSaleId,
                    order.getCreatedAt()
            );
            allOrders.add(newOrderResponse);
        }
        return allOrders;
    }

    @Transactional
    public void updateStatus(Integer id, OrderStatusRequest newStatus) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not Found"));
        order.setStatus(newStatus.getStatus());
    }
}