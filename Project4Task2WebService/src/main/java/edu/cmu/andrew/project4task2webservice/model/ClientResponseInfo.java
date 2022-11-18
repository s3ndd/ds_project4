package edu.cmu.andrew.project4task2webservice.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClientResponseInfo {
    // the response body to the client
    private IResponse responseBody;

    // the response http status code to the client
    private int responseStatusCode;

    // the date of the response received
    private LocalDate date;

    // the time of the response received
    private LocalTime time;



    public ClientResponseInfo() {
    }

    public ClientResponseInfo(IResponse responseBody, int responseStatusCode) {
        this.responseBody = responseBody;
        this.responseStatusCode = responseStatusCode;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public IResponse getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(IResponse responseBody) {
        this.responseBody = responseBody;
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    public void setResponseStatusCode(int responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
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
