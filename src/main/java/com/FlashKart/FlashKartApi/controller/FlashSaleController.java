package com.FlashKart.FlashKartApi.controller;

import com.FlashKart.FlashKartApi.config.CacheNames;
import com.FlashKart.FlashKartApi.dto.FlashSaleRequestDTO;
import com.FlashKart.FlashKartApi.dto.FlashSaleResponseDTO;
import com.FlashKart.FlashKartApi.model.FlashSale;
import com.FlashKart.FlashKartApi.service.FlashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flash-sales")
@CrossOrigin(origins = "*")
public class FlashSaleController {
    @Autowired
    private FlashSaleService flashSaleService;

    @GetMapping
    public ResponseEntity<List<FlashSaleResponseDTO>> displayAllFlashSales(){
        return ResponseEntity.ok(flashSaleService.getAllFlashSales());
    }

    @PostMapping
    public ResponseEntity<FlashSale> createNewFlashSale(@RequestBody FlashSaleRequestDTO newFLashSaleRequest){
        return ResponseEntity.ok(flashSaleService.addNewFlashSale(newFLashSaleRequest));
    }

    @GetMapping("/active")
    public ResponseEntity<List<FlashSaleResponseDTO>> displayAllActiveSales(){
        return ResponseEntity.ok(flashSaleService.getAllActiveSales());
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateFlashSale(@PathVariable Integer id){
        flashSaleService.activateSaleById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlaseSale(@PathVariable Integer id){
        flashSaleService.deleteSaleById(id);
        return ResponseEntity.noContent().build();
    }
}
