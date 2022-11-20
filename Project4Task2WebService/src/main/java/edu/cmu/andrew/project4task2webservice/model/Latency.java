package edu.cmu.andrew.project4task2webservice.model;

import java.util.ArrayList;
import java.util.List;

public class Latency {
    private List<Double> recentRecords;
    private Double average;
    private Double maximum;
    private Double minimum;

    public Latency() {
        recentRecords = new ArrayList<>();
    }

    public Latency(List<Double> recentRecords, Double average, Double maximum, Double minimum) {
        this.recentRecords = recentRecords;
        this.average = average;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public List<Double> getRecentRecords() {
        return recentRecords;
    }

    public void setRecentRecords(List<Double> recentRecords) {
        this.recentRecords = recentRecords;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

}
