package com.example.MagicFridgeAI.service;

import com.example.MagicFridgeAI.WebClientConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class ChatgptService {

    private final WebClient webClient;
    private String chatgptApiKey = "CHATGPT_API_KEY";

    public ChatgptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe(){
        String prompt = "Me sugira uma receita simples com igredientes comuns";
        Map<String,Object> requestBody = Map.of(
                "model", "gpt-4o",
                "messages", List.of(
                    Map.of("role", "system", "content","Você é um assistente que cria receitas de comidas"),
                    Map.of("role", "system", "content",prompt)
                )
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION,"Bearer" + chatgptApiKey )
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                        return message.get("content").toString();
                    }
                    return "Nenhuma receita foi gerada.";
                });
    }

}
