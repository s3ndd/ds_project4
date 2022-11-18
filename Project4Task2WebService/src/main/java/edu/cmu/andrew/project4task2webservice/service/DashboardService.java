package edu.cmu.andrew.project4task2webservice.service;

import edu.cmu.andrew.project4task2webservice.model.Dashboard;
import edu.cmu.andrew.project4task2webservice.repository.LoggingRepository;

public class DashboardService {
    private LoggingRepository repository;

    public DashboardService() {
    }

    public Dashboard generateDashboard() {
        return new Dashboard();
    }
}
