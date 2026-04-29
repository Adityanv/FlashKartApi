package com.FlashKart.FlashKartApi.controller;

import com.FlashKart.FlashKartApi.dto.ProductRequestDTO;
import com.FlashKart.FlashKartApi.dto.ProductResponseDTO;
import com.FlashKart.FlashKartApi.enums.Category;
import com.FlashKart.FlashKartApi.model.Product;
import com.FlashKart.FlashKartApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(@RequestParam(required = false) String search,
                                                                   @RequestParam(required = false) Category category
    ){
        List<ProductResponseDTO> products = productService.filterProducts(search, category);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductRequestDTO newProduct){
        return ResponseEntity.ok(productService.addNewProduct(newProduct));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
