package edu.cmu.andrew.project4task2webservice.model;

import java.util.List;

public class Dashboard {
    private List<String> top20SearchWords;

    private List<String> top20GIFs;

    private Latency serviceLatency;

    private Latency externalAPILatency;

    private DeviceInfo top10Devices;

    private List<String> logs;

    public Dashboard() {
    }

    public List<String> getTop20SearchWords() {
        return top20SearchWords;
    }

    public void setTop20SearchWords(List<String> top20SearchWords) {
        this.top20SearchWords = top20SearchWords;
    }

    public List<String> getTop20GIFs() {
        return top20GIFs;
    }

    public void setTop20GIFs(List<String> top20GIFs) {
        this.top20GIFs = top20GIFs;
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

    public DeviceInfo getTop10Devices() {
        return top10Devices;
    }

    public void setTop10Devices(DeviceInfo top10Devices) {
        this.top10Devices = top10Devices;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }
}

