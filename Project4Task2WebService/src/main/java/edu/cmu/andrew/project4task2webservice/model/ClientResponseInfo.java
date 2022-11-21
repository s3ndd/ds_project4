/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for response information from client.
 */

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

    // constructor of ClientResponseInfo
    public ClientResponseInfo() {
    }

    // constructor of ClientResponseInfo with response content and status(e.g.200,404) input
    public ClientResponseInfo(IResponse responseBody, int responseStatusCode) {
        this.responseBody = responseBody;
        this.responseStatusCode = responseStatusCode;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    // get response content
    public IResponse getResponseBody() {
        return responseBody;
    }

    // set response content
    public void setResponseBody(IResponse responseBody) {
        this.responseBody = responseBody;
    }

    // get status code
    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    // set status code
    public void setResponseStatusCode(int responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }

    // get date of the response
    public LocalDate getDate() {
        return date;
    }

    // set date of the response
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // get time of the response
    public LocalTime getTime() {
        return time;
    }

    // set time of the response
    public void setTime(LocalTime time) {
        this.time = time;
    }
}
