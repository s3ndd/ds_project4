package edu.cmu.andrew.project4task2webservice.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TenorResponseInfo {
    // the raw response body from the 3rd part tenor api
    private String tenorResponseBody;

    // the response http status code from the 3rd part tenor api
    private int tenorResponseStatusCode;

    // the date of the response received
    private LocalDate date;

    // the time of the response received
    private LocalTime time;



    public TenorResponseInfo() {
    }

    public TenorResponseInfo(String tenorResponseBody, int tenorResponseStatusCode) {
        this.tenorResponseBody = tenorResponseBody;
        this.tenorResponseStatusCode = tenorResponseStatusCode;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public String getTenorResponseBody() {
        return tenorResponseBody;
    }

    public void setTenorResponseBody(String tenorResponseBody) {
        this.tenorResponseBody = tenorResponseBody;
    }

    public int getTenorResponseStatusCode() {
        return tenorResponseStatusCode;
    }

    public void setTenorResponseStatusCode(int tenorResponseStatusCode) {
        this.tenorResponseStatusCode = tenorResponseStatusCode;
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

    @Override
    public String toString() {
        return "TenorResponseInfo{" +
                "tenorResponseBody='" + tenorResponseBody + '\'' +
                ", tenorResponseStatusCode=" + tenorResponseStatusCode +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
