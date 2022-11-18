package edu.cmu.andrew.project4task2webservice.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClientRequestInfo {
    private String searchTerm;
    private LocalDate date;
    private LocalTime time;

    public ClientRequestInfo() {
    }

    public ClientRequestInfo(String searchTerm) {
        this.searchTerm = searchTerm;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
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
        return "ClientRequestInfo{" +
                "searchTerm='" + searchTerm + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
