package edu.cmu.andrew.project4task2webservice.service;

import edu.cmu.andrew.project4task2webservice.model.Dashboard;
import edu.cmu.andrew.project4task2webservice.model.DeviceInfo;
import edu.cmu.andrew.project4task2webservice.model.Latency;

import java.util.ArrayList;
import java.util.List;

public class DashboardService {
    Dashboard dashboard = new Dashboard();

    public List<String> getPopularKeyword() {
        //add top twenty popular words, query from db
        List<String> popularKeyword = dashboard.getTop20SearchWords();
        return popularKeyword;
    }

    public List<String> getPopularGIFsURL() {
        List<String> popularGIFsURL = dashboard.getTop20GIFs();
        return popularGIFsURL;
    }

    public Latency getServiceLatency() {
        Latency serviceLatency = dashboard.getServiceLatency();
        return serviceLatency;
    }

    public Latency getExternalAPILatency() {
        Latency externalAPILatency = dashboard.getExternalAPILatency();
        return externalAPILatency;
    }

    public List<DeviceInfo> getTop10Devices() {
        List<DeviceInfo> top10Devices = dashboard.getTop10Devices();
        return top10Devices;
    }

    public List<String> getLogs() {
        List<String> logs = dashboard.getLogs();
        return logs;
    }
}