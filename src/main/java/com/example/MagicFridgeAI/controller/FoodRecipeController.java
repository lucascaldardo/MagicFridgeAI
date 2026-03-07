package com.example.MagicFridgeAI.controller;
import com.example.MagicFridgeAI.model.FooditemModel;
import com.example.MagicFridgeAI.service.FooditemService;
import com.example.MagicFridgeAI.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class FoodRecipeController {

    private FooditemService fooditemService;
    private GeminiService geminiService;

    public FoodRecipeController(FooditemService fooditemService, GeminiService geminiService) {
        this.fooditemService = fooditemService;
        this.geminiService = geminiService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generatRecipe(){
        List<FooditemModel> fooditems = fooditemService.listarTodos();
        return geminiService.generateRecipe(fooditems)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
