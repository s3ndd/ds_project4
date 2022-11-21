/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for Dashboard Object.
 */

package edu.cmu.andrew.project4task2webservice.model;

import java.util.List;

public class Dashboard {
    // list to store top search term
    private List<String> topSearchWords;

    // list to store top GIFs
    private List<String> topGIFs;

    // Latency object for this system service
    private Latency serviceLatency;

    // Latency object for API service
    private Latency externalAPILatency;

    // list to store top devices of frequent users
    private TopDeviceInfo topDevices;

    // list to store system logs
    private List<SystemLog> logs;

    public Dashboard() {
    }

    // get top search term from dashboard
    public List<String> getTopSearchWords() {
        return topSearchWords;
    }

    // set top search term from dashboard
    public void setTopSearchWords(List<String> topSearchWords) {
        this.topSearchWords = topSearchWords;
    }

    // get top GIFs from dashboard
    public List<String> getTopGIFs() {
        return topGIFs;
    }

    // set top GIFs from dashboard
    public void setTopGIFs(List<String> topGIFs) {
        this.topGIFs = topGIFs;
    }

    // get system service latency
    public Latency getServiceLatency() {
        return serviceLatency;
    }

    // set system service latency
    public void setServiceLatency(Latency serviceLatency) {
        this.serviceLatency = serviceLatency;
    }

    // get API service latency
    public Latency getExternalAPILatency() {
        return externalAPILatency;
    }

    // set API service latency
    public void setExternalAPILatency(Latency externalAPILatency) {
        this.externalAPILatency = externalAPILatency;
    }

    // get top devices of frequent users
    public TopDeviceInfo getTopDevices() {
        return topDevices;
    }

    // set top devices of frequent users
    public void setTopDevices(TopDeviceInfo topDevices) {
        this.topDevices = topDevices;
    }

    // get system logs
    public List<SystemLog> getLogs() {
        return logs;
    }

    // set system logs
    public void setLogs(List<SystemLog> logs) {
        this.logs = logs;
    }
}

