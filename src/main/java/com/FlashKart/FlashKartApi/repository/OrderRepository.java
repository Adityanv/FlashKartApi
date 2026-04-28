package com.FlashKart.FlashKartApi.repository;

import com.FlashKart.FlashKartApi.enums.OrderStatus;
import com.FlashKart.FlashKartApi.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository {
    //Find all orders by status
    @Query("SELECT o FROM Order o WHERE o.status = :status")
    List<Order> findByStatus(OrderStatus status);

    //Used for Orders today in stats
    @Query("COUNT o FROM Order o WHERE o.created_at >= :start AND 0.created_at <= :end")
    Integer countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
