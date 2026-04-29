package com.FlashKart.FlashKartApi.repository;

import com.FlashKart.FlashKartApi.dto.ProductResponseDTO;
import com.FlashKart.FlashKartApi.enums.SaleStatus;
import com.FlashKart.FlashKartApi.model.FlashSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlashSaleRepository extends JpaRepository<FlashSale, Integer> {
    //Returns FlashSale by Status
    @Query("SELECT f FROM FlashSale f WHERE f.status = :status")
    List<FlashSale> findByStatus(SaleStatus status);

    //Used by Scheduler to find sales to activate
    @Query("SELECT f FROM FlashSale f WHERE f.status = :status AND f.startTime <= :now")
    List<FlashSale> findByStatusAndStartTimeLessThanEqual(SaleStatus status, LocalDateTime now);

    //Used By Scheduler to Find Sales and End it
    @Query("SELECT f FROM FlashSale f WHERE f.status = :status AND f.endTime <= :now")
    List<FlashSale> findByStatusAndEndTimeLessThanEqual(SaleStatus status, LocalDateTime now);

    FlashSale findByProductId(Integer id);
}
