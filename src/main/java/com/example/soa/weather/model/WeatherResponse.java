package com.example.soa.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
    private String city;
    private String temperature;
    private String description;
    private String humidity;
    @JsonProperty("wind_speed")
    private String windSpeed;
    private java.util.List<ForecastDay> forecast;

    // Getters and Setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public java.util.List<ForecastDay> getForecast() {
        return forecast;
    }

    public void setForecast(java.util.List<ForecastDay> forecast) {
        this.forecast = forecast;
    }
}


