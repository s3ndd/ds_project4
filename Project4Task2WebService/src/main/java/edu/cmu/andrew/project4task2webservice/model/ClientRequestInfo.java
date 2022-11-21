/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for request information from client.
 */

package edu.cmu.andrew.project4task2webservice.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClientRequestInfo {
    // search string from client for GIFs searching
    private String searchTerm;
    // request date
    private LocalDate date;
    // request time
    private LocalTime time;

    // constructor of ClientRequestInfo
    public ClientRequestInfo() {
    }

    // constructor of ClientRequestInfo with input search string
    public ClientRequestInfo(String searchTerm) {
        this.searchTerm = searchTerm;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    // get search string of the request
    public String getSearchTerm() {
        return searchTerm;
    }

    // set search string of the request
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    // get date of the request
    public LocalDate getDate() {
        return date;
    }

    // set date of the request
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // get timing of the request
    public LocalTime getTime() {
        return time;
    }

    // set timing of the request
    public void setTime(LocalTime time) {
        this.time = time;
    }

    // override toString for print out
    @Override
    public String toString() {
        return "ClientRequestInfo{" +
                "searchTerm='" + searchTerm + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
