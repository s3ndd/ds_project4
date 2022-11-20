package edu.cmu.andrew.project4task2webservice.model;

import java.util.List;

public class Dashboard {
    private List<String> topSearchWords;

    private List<String> topGIFs;

    private Latency serviceLatency;

    private Latency externalAPILatency;

    private TopDeviceInfo topDevices;

    private List<SystemLog> logs;

    public Dashboard() {
    }

    public List<String> getTopSearchWords() {
        return topSearchWords;
    }

    public void setTopSearchWords(List<String> topSearchWords) {
        this.topSearchWords = topSearchWords;
    }

    public List<String> getTopGIFs() {
        return topGIFs;
    }

    public void setTopGIFs(List<String> topGIFs) {
        this.topGIFs = topGIFs;
    }

    public Latency getServiceLatency() {
        return serviceLatency;
    }

    public void setServiceLatency(Latency serviceLatency) {
        this.serviceLatency = serviceLatency;
    }

    public Latency getExternalAPILatency() {
        return externalAPILatency;
    }

    public void setExternalAPILatency(Latency externalAPILatency) {
        this.externalAPILatency = externalAPILatency;
    }

    public TopDeviceInfo getTopDevices() {
        return topDevices;
    }

    public void setTopDevices(TopDeviceInfo topDevices) {
        this.topDevices = topDevices;
    }

    public List<SystemLog> getLogs() {
        return logs;
    }

    public void setLogs(List<SystemLog> logs) {
        this.logs = logs;
    }
}

