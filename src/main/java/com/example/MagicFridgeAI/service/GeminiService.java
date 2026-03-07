package com.example.MagicFridgeAI.service;

import com.example.MagicFridgeAI.model.FooditemModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GeminiService {


    private FooditemService fooditemService;

    private final WebClient webClient;

    @Value("${GEMENI_API_KEY}")
    private String geminiApiKey;

    public GeminiService(WebClient webClient, FooditemService fooditemService) {
        this.webClient = webClient;
        this.fooditemService = fooditemService;
    }

    public Mono<String> generateRecipe(List<FooditemModel> fooditems){

        String alimentos = fooditems.stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, Validade: %s",
                        item.getNome(), item.getCategoria(),item.getQuantidade(), item.getValidade()))
                .collect(Collectors.joining("\n"));

        String prompt = "Baseado no meu Banco de Dados faça uma receita com os seguintes ingredientes:\n " + alimentos;

        Map<String,Object> requestBody = Map.of(
               "contents", List.of(
                       Map.of("parts",List.of(
                               Map.of("text",prompt)
                       ))
                )
        );


        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("x-goog-api-key", geminiApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var candidates = (List<Map<String, Object>>) response.get("candidates");
                    if (candidates != null && !candidates.isEmpty()) {
                        var content = (Map<String, Object>) candidates.get(0).get("content");
                        var parts = (List<Map<String, Object>>) content.get("parts");
                        if (parts != null && !parts.isEmpty()) {
                            return parts.get(0).get("text").toString();
                        }
                    }
                    return "Nenhuma receita foi gerada.";
                });
    }
}
