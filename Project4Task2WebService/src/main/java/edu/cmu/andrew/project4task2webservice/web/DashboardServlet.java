package edu.cmu.andrew.project4task2webservice.web;

import edu.cmu.andrew.project4task2webservice.service.DashboardService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Dashboard", value = "/api/v1/dashboard")
public class DashboardServlet extends HttpServlet {
    private DashboardService dashboardService;

    public void init() {
        dashboardService = new DashboardService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
