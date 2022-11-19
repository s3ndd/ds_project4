/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for response information from Tenor.
 *
 */

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

    //constructor of TenorResponseInfo
    public TenorResponseInfo() {
    }

    //constructor of TenorResponseInfo with the raw response body and response http status code from API
    public TenorResponseInfo(String tenorResponseBody, int tenorResponseStatusCode) {
        this.tenorResponseBody = tenorResponseBody;
        this.tenorResponseStatusCode = tenorResponseStatusCode;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    //get the raw response body
    public String getTenorResponseBody() {
        return tenorResponseBody;
    }

    //set the raw response body
    public void setTenorResponseBody(String tenorResponseBody) {
        this.tenorResponseBody = tenorResponseBody;
    }

    //get response http status code
    public int getTenorResponseStatusCode() {
        return tenorResponseStatusCode;
    }

    //set response http status code
    public void setTenorResponseStatusCode(int tenorResponseStatusCode) {
        this.tenorResponseStatusCode = tenorResponseStatusCode;
    }

    //get local date of the response received
    public LocalDate getDate() {
        return date;
    }

    //set local date of the response received
    public void setDate(LocalDate date) {
        this.date = date;
    }

    //get local time of the response received
    public LocalTime getTime() {
        return time;
    }

    //set local time of the response received
    public void setTime(LocalTime time) {
        this.time = time;
    }

    //override toString for print out
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
