package edu.cmu.andrew.project4task2webservice.web;

import edu.cmu.andrew.project4task2webservice.model.ClientRequestInfo;
import edu.cmu.andrew.project4task2webservice.model.ClientResponseInfo;
import edu.cmu.andrew.project4task2webservice.model.IResponse;
import edu.cmu.andrew.project4task2webservice.model.LogEvent;
import edu.cmu.andrew.project4task2webservice.service.GIFBotService;
import edu.cmu.andrew.project4task2webservice.service.LoggingService;
import edu.cmu.andrew.project4task2webservice.util.Response;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static edu.cmu.andrew.project4task2webservice.util.Request.fetchRequestDevice;
import static edu.cmu.andrew.project4task2webservice.util.Request.generateRequestID;
import static edu.cmu.andrew.project4task2webservice.util.Response.buildResponseBody;


@WebServlet(name = "GIFBot", value = "/api/v1/gif")
public class GIFBotServlet extends HttpServlet {
    private GIFBotService gifBotService;

    private LoggingService loggingService;

    public void init() {
        loggingService = new LoggingService();
        gifBotService = new GIFBotService(loggingService);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long start = System.currentTimeMillis();
        String requestID = generateRequestID();
        String searchTerm = request.getParameter("search");
        LogEvent logEvent = new LogEvent(requestID);
        logEvent.setClientRequestInfo(new ClientRequestInfo(searchTerm));
        logEvent.setRequestDeviceInfo(fetchRequestDevice(request));
        loggingService.save(logEvent);
        IResponse responseData = gifBotService.search(requestID, searchTerm);
        long latency = System.currentTimeMillis() - start;
        String responseBody = buildResponseBody(responseData);
        logEvent.setClientResponseInfo(new ClientResponseInfo(responseData, responseData.getStatusCode()));
        logEvent.setServiceLatency(latency);
        loggingService.save(logEvent);
        Response.respondWithData(response, responseBody, responseData.getStatusCode());
    }

    public void destroy() {
    }
}