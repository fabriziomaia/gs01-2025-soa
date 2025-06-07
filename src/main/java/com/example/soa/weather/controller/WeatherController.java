package com.example.soa.weather.controller;

import com.example.soa.weather.model.WeatherResponse;
import com.example.soa.weather.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/{cityName}")
    public ResponseEntity<WeatherResponse> getWeather(@PathVariable String cityName) {
        String formattedCityName = cityName.replace(" ", "%20");
        WeatherResponse weatherData = weatherService.getFormattedWeather(formattedCityName);

        if (weatherData.getDescription() != null && weatherData.getDescription().contains("Erro")) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, weatherData.getDescription());
        }

        return ResponseEntity.ok(weatherData);
    }
}


