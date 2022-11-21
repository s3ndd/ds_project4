/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for latency records and statistics.
 */

package edu.cmu.andrew.project4task2webservice.model;

import java.util.ArrayList;
import java.util.List;

public class Latency {
    // list to store latency data
    private List<Double> recentRecords;

    // average of latency data
    private Double average;

    // maximum of latency data
    private Double maximum;

    // minimum of latency data
    private Double minimum;

    // Constructor of latency and with empty data
    public Latency() {
        recentRecords = new ArrayList<>();
    }

    // Constructor of latency with data, avg., max., and min. input
    public Latency(List<Double> recentRecords, Double average, Double maximum, Double minimum) {
        this.recentRecords = recentRecords;
        this.average = average;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    // get latency data list
    public List<Double> getRecentRecords() {
        return recentRecords;
    }

    // set latency data list
    public void setRecentRecords(List<Double> recentRecords) {
        this.recentRecords = recentRecords;
    }

    // get average of latency data
    public Double getAverage() {
        return average;
    }

    // set average of latency data
    public void setAverage(Double average) {
        this.average = average;
    }

    // get maximum of latency data
    public Double getMaximum() {
        return maximum;
    }

    // set maximum of latency data
    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }

    // get minimum of latency data
    public Double getMinimum() {
        return minimum;
    }

    // set minimum of latency data
    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

}
