package com.example.soa.weather.service;

import com.example.soa.weather.client.HgWeatherClient;
import com.example.soa.weather.model.ForecastDay;
import com.example.soa.weather.model.WeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private final HgWeatherClient weatherClient;

    public WeatherService(HgWeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public WeatherResponse getFormattedWeather(String cityName) {
        JsonNode rawData = weatherClient.getWeatherByCity(cityName);

        if (rawData.has("error") && rawData.get("error").asBoolean()) {
            WeatherResponse errorResponse = new WeatherResponse();
            errorResponse.setDescription(rawData.path("message").asText());
            return errorResponse;
        }

        JsonNode results = rawData.get("results");
        if (results == null || !results.isObject()) {
            WeatherResponse errorResponse = new WeatherResponse();
            errorResponse.setDescription("Resposta inesperada da API HG Weather.");
            return errorResponse;
        }

        WeatherResponse formattedData = new WeatherResponse();
        formattedData.setCity(results.path("city_name").asText("N/A"));
        formattedData.setTemperature(results.path("temp").asText("N/A"));
        formattedData.setDescription(results.path("description").asText("N/A"));
        formattedData.setHumidity(results.path("humidity").asText("N/A"));
        formattedData.setWindSpeed(results.path("wind_speedy").asText("N/A"));

        List<ForecastDay> forecastList = new ArrayList<>();
        JsonNode forecastNode = results.get("forecast");
        if (forecastNode != null && forecastNode.isArray()) {
            for (int i = 0; i < Math.min(forecastNode.size(), 3); i++) {
                JsonNode day = forecastNode.get(i);
                ForecastDay forecastDay = new ForecastDay();
                forecastDay.setDate(day.path("date").asText());
                forecastDay.setWeekday(day.path("weekday").asText());
                forecastDay.setMaxTemp(day.path("max").asText());
                forecastDay.setMinTemp(day.path("min").asText());
                forecastDay.setDescription(day.path("description").asText());
                forecastList.add(forecastDay);
            }
        }
        formattedData.setForecast(forecastList);

        return formattedData;
    }
}


