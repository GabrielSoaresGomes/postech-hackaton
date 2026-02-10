package com.postech.hackaton.interface_adapter.gateways;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.hackaton.application.gateways.AiTriageGateway;
import com.postech.hackaton.dtos.transfer.ai_triage.AiTriageRequestDTO;
import com.postech.hackaton.dtos.transfer.ai_triage.AiTriageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OllamaAiTriageGatewayImpl implements AiTriageGateway {

    private final RestClient aiRestClient;
    private final ObjectMapper objectMapper;

    @Value("${ai.ollama.model:llama3.1}")
    private String model;

    @Override
    public AiTriageResponseDTO classify(AiTriageRequestDTO request) {
        Map<String, Object> payload = Map.of(
                "model", model,
                "stream", false,
                "options", Map.of(
                    "temperature", 0.0,
                    "top_p", 0.1,
                    "num_predict", 200
                ),
                "messages", List.of(
                        Map.of("role", "user", "content", request.prompt())
                )
        );

        OllamaChatResponse raw = aiRestClient.post()
                .uri("/api/chat")
                .body(payload)
                .retrieve()
                .body(OllamaChatResponse.class);

        if (raw == null || raw.message == null || raw.message.content == null) {
            throw new IllegalStateException("Resposta inválida da IA (Ollama).");
        }

        String content = raw.message.content;

        String json = extractFirstJsonObject(content);

        try {
            return objectMapper.readValue(json, AiTriageResponseDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Falha ao parsear JSON da IA: " + content, e);
        }
    }

    private String extractFirstJsonObject(String text) {
        int start = text.indexOf('{');
        int end = text.lastIndexOf('}');
        if (start < 0 || end < 0 || end <= start) {
            throw new IllegalStateException("IA não retornou JSON. Conteúdo: " + text);
        }
        return text.substring(start, end + 1);
    }

    public static class OllamaChatResponse {
        public Message message;

        public static class Message {
            public String role;
            public String content;
        }
    }
}
