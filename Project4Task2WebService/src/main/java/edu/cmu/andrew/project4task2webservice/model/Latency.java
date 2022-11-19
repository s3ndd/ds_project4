/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for latency records and statistics.
 *
 */

package edu.cmu.andrew.project4task2webservice.model;

import java.util.List;

public class Latency {

    //list to store latency data
    private List<Long> recentRecords;
    //average of latency data
    private Long average;
    //maximum of latency data
    private Long maximum;
    //minimum of latency data
    private Long minimum;

    //Constructor of latency with data, avg., max., and min. input
    public Latency(List<Long> recentRecords, Long average, Long maximum, Long minimum) {
        this.recentRecords = recentRecords;
        this.average = average;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    //get latency data list
    public List<Long> getRecentRecords() {
        return recentRecords;
    }

    //set latency data list
    public void setRecentRecords(List<Long> recentRecords) {
        this.recentRecords = recentRecords;
    }

    //get average of latency data
    public Long getAverage() {
        return average;
    }

    //set average of latency data
    public void setAverage(Long average) {
        this.average = average;
    }

    //get maximum of latency data
    public Long getMaximum() {
        return maximum;
    }

    //set maximum of latency data
    public void setMaximum(Long maximum) {
        this.maximum = maximum;
    }

    //get minimum of latency data
    public Long getMinimum() {
        return minimum;
    }

    //set minimum of latency data
    public void setMinimum(Long minimum) {
        this.minimum = minimum;
    }
}
