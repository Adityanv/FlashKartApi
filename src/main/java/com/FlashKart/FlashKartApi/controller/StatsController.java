package com.FlashKart.FlashKartApi.controller;

import com.FlashKart.FlashKartApi.dto.StatsResponseDTO;
import com.FlashKart.FlashKartApi.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "*")
public class StatsController {

    @Autowired
    private StatsService service;

    @GetMapping
    public ResponseEntity<StatsResponseDTO> getAllStats(){
        return ResponseEntity.ok(service.getAllStats());
    }
}
