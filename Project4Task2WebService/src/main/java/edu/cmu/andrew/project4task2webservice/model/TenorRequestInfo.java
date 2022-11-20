package edu.cmu.andrew.project4task2webservice.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TenorRequestInfo {
    private String baseUrl;
    private String uri;

    private LocalDate date;

    private LocalTime time;

    public TenorRequestInfo() {
    }

    public TenorRequestInfo(String baseUrl, String uri) {
        this.baseUrl = baseUrl;
        this.uri = uri;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

}
