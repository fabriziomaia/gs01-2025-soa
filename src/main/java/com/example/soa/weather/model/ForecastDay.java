package com.example.soa.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForecastDay {
    private String date;
    private String weekday;
    @JsonProperty("max_temp")
    private String maxTemp;
    @JsonProperty("min_temp")
    private String minTemp;
    private String description;

    // Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


