package com.FlashKart.FlashKartApi.service;

import com.FlashKart.FlashKartApi.dto.StatsResponseDTO;
import com.FlashKart.FlashKartApi.enums.SaleStatus;
import com.FlashKart.FlashKartApi.repository.FlashSaleRepository;
import com.FlashKart.FlashKartApi.repository.OrderRepository;
import com.FlashKart.FlashKartApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class StatsService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FlashSaleRepository flashSaleRepository;

    public StatsResponseDTO getAllStats(){
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atTime(LocalTime.MIN);
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);
        StatsResponseDTO stats = new StatsResponseDTO();
        long totalProducts = productRepository.count();
        Integer activeSales = flashSaleRepository.findByStatus(SaleStatus.ACTIVE).size();
        long ordersToday = orderRepository.countByCreatedAtBetween(startOfDay, endOfDay);
        Integer lowStockCount = productRepository.getAllLowStockProducts().size();
        stats.setActiveSales(activeSales);
        stats.setOrdersToday(ordersToday);
        stats.setLowStockCount(lowStockCount);
        stats.setTotalProducts(totalProducts);
        return stats;
    }
}
