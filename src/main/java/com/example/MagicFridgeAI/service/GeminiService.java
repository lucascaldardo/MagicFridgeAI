package com.example.MagicFridgeAI.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private final WebClient webClient;

    @Value("${GEMENI_API_KEY}")
    private String geminiApiKey;

    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe(){
        String prompt = "Me sugira uma receita simples com igredientes comuns";

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
