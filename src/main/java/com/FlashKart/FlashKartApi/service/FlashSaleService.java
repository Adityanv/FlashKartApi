package com.FlashKart.FlashKartApi.service;

import com.FlashKart.FlashKartApi.config.CacheNames;
import com.FlashKart.FlashKartApi.dto.FlashSaleRequestDTO;
import com.FlashKart.FlashKartApi.dto.FlashSaleResponseDTO;
import com.FlashKart.FlashKartApi.dto.ProductResponseDTO;
import com.FlashKart.FlashKartApi.enums.SaleStatus;
import com.FlashKart.FlashKartApi.model.FlashSale;
import com.FlashKart.FlashKartApi.model.Product;
import com.FlashKart.FlashKartApi.repository.FlashSaleRepository;
import com.FlashKart.FlashKartApi.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlashSaleService {

    @Autowired
    private FlashSaleRepository flashSaleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value = CacheNames.FLASH_SALES)
    public List<FlashSaleResponseDTO> getAllFlashSales() {
        List<FlashSale> flashSaleList = flashSaleRepository.findAllWithProduct();
        List<FlashSaleResponseDTO> flashSales = new ArrayList<>();

        for(FlashSale sale : flashSaleList) {
            Product p = sale.getProduct();
            Integer discountPercent = sale.getDiscountPercent();

            BigDecimal discountMultiplier = BigDecimal.valueOf(1)
                    .subtract(BigDecimal.valueOf(discountPercent).divide(BigDecimal.valueOf(100)));
            BigDecimal salePrice = p.getPrice().multiply(discountMultiplier);

            FlashSaleResponseDTO flashSale = new FlashSaleResponseDTO(
                    sale.getId(), p.getId(), p.getName(), p.getEmoji(), p.getCategory(),
                    p.getPrice(), discountPercent, salePrice, p.getStock(),
                    sale.getStartTime(), sale.getEndTime(), sale.getStatus()
            );
            flashSales.add(flashSale);
        }
        return flashSales;
    }

    @CacheEvict(value = {CacheNames.FLASH_SALES, CacheNames.ACTIVE_SALES}, allEntries = true)
    public FlashSale addNewFlashSale(FlashSaleRequestDTO newFlashSaleRequest) {
        Product p = productRepository.findById(newFlashSaleRequest.getProductId()).orElseThrow(() -> new EntityNotFoundException("Product ID " + newFlashSaleRequest.getProductId() + " Not Found"));
        if(
                newFlashSaleRequest.getEndTime().isBefore(newFlashSaleRequest.getStartTime()) ||
                        newFlashSaleRequest.getStartTime().isEqual(newFlashSaleRequest.getEndTime())
        ){
            throw new RuntimeException("Sart time should be scheduled before End time");
        }
        BigDecimal currPrice = p.getPrice();
        int discountPercent = newFlashSaleRequest.getDiscountPercent();

        BigDecimal discountMultiplier = BigDecimal.valueOf(1)
                .subtract(BigDecimal.valueOf(discountPercent).divide(BigDecimal.valueOf(100)));
        BigDecimal salePrice = p.getPrice().multiply(discountMultiplier);

        FlashSale newFlashSale = new FlashSale(p, newFlashSaleRequest.getDiscountPercent(), salePrice, newFlashSaleRequest.getSaleStock(), newFlashSaleRequest.getStartTime(), newFlashSaleRequest.getEndTime(), SaleStatus.SCHEDULED, LocalDateTime.now());
        return flashSaleRepository.save(newFlashSale);
    }

    @Cacheable(value = CacheNames.ACTIVE_SALES)
    public List<FlashSaleResponseDTO> getAllActiveSales() {
        // 1. Call the new repository method that uses JOIN FETCH
        List<FlashSale> activeSalesList = flashSaleRepository.findByStatusWithProduct(SaleStatus.ACTIVE);
        List<FlashSaleResponseDTO> activeSales = new ArrayList<>();

        // 2. Loop and construct DTOs (this will now succeed without an exception)
        for (FlashSale sale : activeSalesList) {
            Product p = sale.getProduct();
            Integer discountPercent = sale.getDiscountPercent();

            BigDecimal discountMultiplier = BigDecimal.valueOf(1)
                    .subtract(BigDecimal.valueOf(discountPercent).divide(BigDecimal.valueOf(100)));
            BigDecimal salePrice = p.getPrice().multiply(discountMultiplier);

            FlashSaleResponseDTO flashSale = new FlashSaleResponseDTO(
                    sale.getId(), p.getId(), p.getName(), p.getEmoji(), p.getCategory(),
                    p.getPrice(), discountPercent, salePrice, p.getStock(),
                    sale.getStartTime(), sale.getEndTime(), sale.getStatus()
            );
            activeSales.add(flashSale);
        }
        return activeSales;
    }

    @Transactional
    @CacheEvict(value = {CacheNames.FLASH_SALES, CacheNames.ACTIVE_SALES}, allEntries = true)
    public void activateSaleById(Integer id) {
        FlashSale sale = flashSaleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sale not found"));
        sale.setStatus(SaleStatus.ACTIVE);
    }

    @Transactional
    @CacheEvict(value = {CacheNames.FLASH_SALES, CacheNames.ACTIVE_SALES}, allEntries = true)
    public void deleteSaleById(Integer id) {
        flashSaleRepository.deleteById(id);
    }
}
