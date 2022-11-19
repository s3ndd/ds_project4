/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for dashboard service.
 *
 */

package edu.cmu.andrew.project4task2webservice.service;

import edu.cmu.andrew.project4task2webservice.model.Dashboard;
import edu.cmu.andrew.project4task2webservice.model.DeviceInfo;
import edu.cmu.andrew.project4task2webservice.model.Latency;

import java.util.List;

public class DashboardService {
    //create dashboard object
    Dashboard dashboard = new Dashboard();

    //get top 20 popular search keywords list
    public List<String> getPopularKeyword() {
        //list to store top twenty popular words
        List<String> popularKeyword = dashboard.getTop20SearchWords();
        return popularKeyword;
    }

    //get top 20 FIFs list
    public List<String> getPopularGIFsURL() {
        //list to store top twenty FIFs
        List<String> popularGIFsURL = dashboard.getTop20GIFs();
        return popularGIFsURL;
    }

    //get system service latency
    public Latency getServiceLatency() {
        //create latency object to store system data
        Latency serviceLatency = dashboard.getServiceLatency();
        return serviceLatency;
    }

    //get API service latency
    public Latency getExternalAPILatency() {
        //create latency object to store API data
        Latency externalAPILatency = dashboard.getExternalAPILatency();
        return externalAPILatency;
    }

    //get top 10 devices of frequent users
    public List<DeviceInfo> getTop10Devices() {
        //list to store top 10 devices
        List<DeviceInfo> top10Devices = dashboard.getTop10Devices();
        return top10Devices;
    }

    //get system logs
    public List<String> getLogs() {
        //list to store system logs
        List<String> logs = dashboard.getLogs();
        return logs;
    }
}