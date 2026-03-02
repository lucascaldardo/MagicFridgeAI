package com.example.MagicFridgeAI.controller;

import com.example.MagicFridgeAI.service.FooditemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FooditemController {
    private FooditemService fooditemService;

    public FooditemController(FooditemService fooditemService) {
        this.fooditemService = fooditemService;
    }



}
