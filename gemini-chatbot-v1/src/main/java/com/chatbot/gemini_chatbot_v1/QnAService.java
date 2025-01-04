package com.chatbot.gemini_chatbot_v1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class QnAService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.Key}")
    private String geminiApiKey;

    private final WebClient webClient;

    public QnAService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    public String getAnswer(String question)
    {
        Map<String,Object> requestBody = Map.of(
                "contents" , new Object[]
                        {
                                Map.of("parts",new Object[]{
                                        Map.of("text", question)
                                })
                        }
        );
        // Make API call


        return webClient.post()
                    .uri(geminiApiUrl+geminiApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
    }
}
