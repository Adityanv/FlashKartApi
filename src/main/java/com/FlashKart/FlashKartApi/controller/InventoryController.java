package com.FlashKart.FlashKartApi.controller;

import com.FlashKart.FlashKartApi.dto.ProductResponseDTO;
import com.FlashKart.FlashKartApi.dto.StockUpdateRequestDTO;
import com.FlashKart.FlashKartApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class InventoryController {
    @Autowired
    private ProductService productService;

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponseDTO>> getAllLowStockProducts(){
        return ResponseEntity.ok(productService.getAllLowStockProducts());

    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Void> restockProduct(@PathVariable Integer id, @RequestBody StockUpdateRequestDTO stockUpdateRequestDTO){
        productService.restockProduct(id, stockUpdateRequestDTO);
        return ResponseEntity.noContent().build();
    }
}
