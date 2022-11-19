/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for Dashboard Object.
 *
 */

package edu.cmu.andrew.project4task2webservice.model;

import java.util.List;

public class Dashboard {

    //list to store top 20 search term
    private List<String> top20SearchWords;

    //list to store top 20 GIFs
    private List<String> top20GIFs;

    //Latency object for this system service
    private Latency serviceLatency;

    //Latency object for API service
    private Latency externalAPILatency;

    //list to store top 10 devices of frequent users
    private List<DeviceInfo> top10Devices;

    //list to store system logs
    private List<String> logs;

    //constructor of Dashboard
    public Dashboard() {
    }

    //get top 20 search term from dashboard
    public List<String> getTop20SearchWords() {
        return top20SearchWords;
    }

    //set top 20 search term from dashboard
    public void setTop20SearchWords(List<String> top20SearchWords) {
        this.top20SearchWords = top20SearchWords;
    }

    //get top 20 FIFs from dashboard
    public List<String> getTop20GIFs() {
        return top20GIFs;
    }

    //set top 20 FIFs from dashboard
    public void setTop20GIFs(List<String> top20GIFs) {
        this.top20GIFs = top20GIFs;
    }

    //get system service latency
    public Latency getServiceLatency() {
        return serviceLatency;
    }

    //set system service latency
    public void setServiceLatency(Latency serviceLatency) {
        this.serviceLatency = serviceLatency;
    }

    //get API service latency
    public Latency getExternalAPILatency() {
        return externalAPILatency;
    }

    //set API service latency
    public void setExternalAPILatency(Latency externalAPILatency) {
        this.externalAPILatency = externalAPILatency;
    }

    //get top 10 devices of frequent users
    public List<DeviceInfo> getTop10Devices() {
        return top10Devices;
    }

    //set top 10 devices of frequent users
    public void setTop10Devices(List<DeviceInfo> top10Devices) {
        this.top10Devices = top10Devices;
    }

    //get system logs
    public List<String> getLogs() {
        return logs;
    }

    //set system logs
    public void setLogs(List<String> logs) {
        this.logs = logs;
    }
}

