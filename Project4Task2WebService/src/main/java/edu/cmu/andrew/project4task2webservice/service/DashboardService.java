package edu.cmu.andrew.project4task2webservice.service;

import edu.cmu.andrew.project4task2webservice.model.Dashboard;
import edu.cmu.andrew.project4task2webservice.repository.LoggingRepository;

import java.util.ArrayList;
import java.util.List;

public class DashboardService {
    private LoggingRepository loggingRepository;

    public DashboardService() {
        loggingRepository = new LoggingRepository();
    }

    public Dashboard generateDashboard() {
        Dashboard dashboard = new Dashboard();
        dashboard.setTopSearchWords(loggingRepository.getTopSearchWords(5));
        dashboard.setTopGIFs(loggingRepository.getTopGIFs(5));
        dashboard.setServiceLatency(loggingRepository.getServiceLatency(5));
        dashboard.setExternalAPILatency(loggingRepository.getExternalAPILatency(5));
        dashboard.setTopDevices(loggingRepository.getTopDevices(5));
        dashboard.setLogs(loggingRepository.getFormattedLogs(100));
        return dashboard;
    }
}