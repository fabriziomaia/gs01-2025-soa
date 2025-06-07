package com.example.soa.weather.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class HgWeatherClient {

    private final String apiKey = System.getenv("HG_WEATHER_API_KEY");
    private final String baseUrl = "https://api.hgbrasil.com/weather";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public HgWeatherClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public JsonNode getWeatherByCity(String cityName) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("key", apiKey != null ? apiKey : "SUA-CHAVE")
                .queryParam("city_name", cityName)
                .queryParam("format", "json-cors")
                .toUriString();

        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);

            if (root != null && !root.path("valid_key").asBoolean() && (apiKey == null || apiKey.equals("SUA-CHAVE"))) {
                System.out.println("Aviso: Chave de API padrão ou inválida. Acesso limitado.");
            } else if (root != null && !root.path("valid_key").asBoolean()) {
                System.out.println("Aviso: Chave de API " + apiKey + " inválida. Acesso limitado.");
            }

            return root;
        } catch (Exception e) {
            System.err.println("Erro ao conectar à API HG Weather: " + e.getMessage());
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("error", true);
            errorMap.put("message", "Erro de conexão com a API: " + e.getMessage());
            return objectMapper.valueToTree(errorMap);
        }
    }
}


