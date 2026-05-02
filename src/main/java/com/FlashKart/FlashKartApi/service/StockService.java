package com.FlashKart.FlashKartApi.service;

import com.FlashKart.FlashKartApi.config.CacheNames;
import com.FlashKart.FlashKartApi.enums.SaleStatus;
import com.FlashKart.FlashKartApi.event.OrderPlacedEvent;
import com.FlashKart.FlashKartApi.model.FlashSale;
import com.FlashKart.FlashKartApi.model.Product;
import com.FlashKart.FlashKartApi.repository.FlashSaleRepository;
import com.FlashKart.FlashKartApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StockService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FlashSaleRepository flashSaleRepository;

    @Autowired
    private CacheManager cacheManager;

    @EventListener
    @Transactional
    public void handleOrderPlaced(OrderPlacedEvent event){
        Product product = productRepository.findById(event.getPrductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        if(event.getFlashSaleId() != null){
            FlashSale sale = flashSaleRepository.findById(event.getFlashSaleId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flash Sale not found"));
            sale.setSaleStock(sale.getSaleStock() - event.getQuantity());
            if(sale.getSaleStock() <= 0){
                sale.setStatus(SaleStatus.ENDED);
            }
            flashSaleRepository.save(sale);
        }
        else{
            product.setStock(product.getStock() - event.getQuantity());
            productRepository.save(product);
        }

        Cache productsCache = cacheManager.getCache(CacheNames.PRODUCTS);
        if(productsCache != null){
            productsCache.clear();
        }
    }
}
