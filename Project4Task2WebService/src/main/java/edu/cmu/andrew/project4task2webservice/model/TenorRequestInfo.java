/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for request information to Tenor.
 */

package edu.cmu.andrew.project4task2webservice.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TenorRequestInfo {

    // Tenor API url
    private String baseUrl;

    // Uniform Resource Identifier
    private String uri;

    // Tenor request local date
    private LocalDate date;

    // Tenor request local time
    private LocalTime time;

    // constructor of TenorRequestInfo
    public TenorRequestInfo() {
    }

    // constructor of TenorRequestInfo with Tenor API url and uri input
    public TenorRequestInfo(String baseUrl, String uri) {
        this.baseUrl = baseUrl;
        this.uri = uri;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    // get Tenor API url
    public String getBaseUrl() {
        return baseUrl;
    }

    // set Tenor API url
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    // get Uniform Resource Identifier
    public String getUri() {
        return uri;
    }

    // set Uniform Resource Identifier
    public void setUri(String uri) {
        this.uri = uri;
    }

    // get Tenor request local date
    public LocalDate getDate() {
        return date;
    }

    // set Tenor request local date
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // get Tenor request local time
    public LocalTime getTime() {
        return time;
    }

    // set Tenor request local time
    public void setTime(LocalTime time) {
        this.time = time;
    }

    // override toString for print out
    @Override
    public String toString() {
        return "TenorRequestInfo{" +
                "baseUrl='" + baseUrl + '\'' +
                ", uri='" + uri + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
