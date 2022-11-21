/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for dashboard service.
 */

package edu.cmu.andrew.project4task2webservice.service;

import edu.cmu.andrew.project4task2webservice.model.Dashboard;
import edu.cmu.andrew.project4task2webservice.repository.LoggingRepository;

public class DashboardService {
    // inject the LoggingRepository
    private LoggingRepository loggingRepository;

    // build a DashboardService instance
    public DashboardService() {
        loggingRepository = new LoggingRepository();
    }

    // generateDashboard returns a Dashboard object which aggregates the analysis data
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