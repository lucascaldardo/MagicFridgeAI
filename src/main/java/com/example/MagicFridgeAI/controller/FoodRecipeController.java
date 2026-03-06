package com.example.MagicFridgeAI.controller;
import com.example.MagicFridgeAI.model.FooditemModel;
import com.example.MagicFridgeAI.service.ChatgptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FoodRecipeController {

    private ChatgptService chatgptService;

    public FoodRecipeController(ChatgptService chatgptService) {
        this.chatgptService = chatgptService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generatRecipe(){
        return chatgptService.generateRecipe();
    }

}
