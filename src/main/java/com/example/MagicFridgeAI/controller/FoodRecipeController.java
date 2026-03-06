package com.example.MagicFridgeAI.controller;
import com.example.MagicFridgeAI.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FoodRecipeController {

    private GeminiService geminiService;

    public FoodRecipeController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generatRecipe(){
        return geminiService.generateRecipe()
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
