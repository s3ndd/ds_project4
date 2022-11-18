package edu.cmu.andrew.project4task2webservice.model;

import java.util.List;

public class Latency {
    private List<Long> recentRecords;
    private Long average;
    private Long maximum;
    private Long minimum;

    public Latency(List<Long> recentRecords, Long average, Long maximum, Long minimum) {
        this.recentRecords = recentRecords;
        this.average = average;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public List<Long> getRecentRecords() {
        return recentRecords;
    }

    public void setRecentRecords(List<Long> recentRecords) {
        this.recentRecords = recentRecords;
    }

    public Long getAverage() {
        return average;
    }

    public void setAverage(Long average) {
        this.average = average;
    }

    public Long getMaximum() {
        return maximum;
    }

    public void setMaximum(Long maximum) {
        this.maximum = maximum;
    }

    public Long getMinimum() {
        return minimum;
    }

    public void setMinimum(Long minimum) {
        this.minimum = minimum;
    }
}
