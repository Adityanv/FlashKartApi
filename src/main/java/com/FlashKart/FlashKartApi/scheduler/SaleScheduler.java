package com.FlashKart.FlashKartApi.scheduler;

import com.FlashKart.FlashKartApi.config.CacheNames;
import com.FlashKart.FlashKartApi.enums.SaleStatus;
import com.FlashKart.FlashKartApi.model.FlashSale;
import com.FlashKart.FlashKartApi.repository.FlashSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SaleScheduler {

    @Autowired
    private FlashSaleRepository flashSaleRepository;

    @Autowired
    private CacheManager cacheManager;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void activateScheduledSales(){
        LocalDateTime now = LocalDateTime.now();
        List<FlashSale> toActivate = flashSaleRepository.findByStatusAndStartTimeLessThanEqual(SaleStatus.SCHEDULED, now);

        if(!toActivate.isEmpty()){
            toActivate.forEach(s -> s.setStatus(SaleStatus.ACTIVE));
            flashSaleRepository.saveAll(toActivate);
            evictSaleCaches();
        }
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void endExpiredSales(){
        LocalDateTime now = LocalDateTime.now();
        List<FlashSale> toEnd = flashSaleRepository.findByStatusAndEndTimeLessThanEqual(SaleStatus.ACTIVE, now);

        if(!toEnd.isEmpty()){
            toEnd.forEach(s -> s.setStatus(SaleStatus.ENDED));
            flashSaleRepository.saveAll(toEnd);
            evictSaleCaches();
        }
    }

    private void evictSaleCaches(){
        Cache salesCache = cacheManager.getCache(CacheNames.FLASH_SALES);
        Cache activeCache = cacheManager.getCache(CacheNames.ACTIVE_SALES);

        if(salesCache != null){
            salesCache.clear();
        }
        if(activeCache != null){
            activeCache.clear();
        }
    }
}
