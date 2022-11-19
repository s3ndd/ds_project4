/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for GIFBot servlet.
 *
 */

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
    //business model for GIFBot
    private GIFBotService gifBotService;

    //logging service
    private LoggingService loggingService;

    //initiate this servlet by instantiating the model
    public void init() {
        loggingService = new LoggingService();
        gifBotService = new GIFBotService(loggingService);
    }

    //servlet will reply to HTTP GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //save API service start time
        long start = System.currentTimeMillis();
        //generate new request ID
        String requestID = generateRequestID();
        //get search term from request
        String searchTerm = request.getParameter("search");
        //create request event log
        LogEvent logEvent = new LogEvent(requestID);
        //set client request information in event log
        logEvent.setClientRequestInfo(new ClientRequestInfo(searchTerm));
        //set client device information in event log
        logEvent.setRequestDeviceInfo(fetchRequestDevice(request));
        //logging service to save event log
        loggingService.save(logEvent);
        //get response of searching gifs via GIFBot service
        IResponse responseData = gifBotService.search(requestID, searchTerm);
        //save API service latency
        long latency = System.currentTimeMillis() - start;
        //response body content
        String responseBody = buildResponseBody(responseData);
        //set response info to event log
        logEvent.setClientResponseInfo(new ClientResponseInfo(responseData, responseData.getStatusCode()));
        //set latency to event log
        logEvent.setServiceLatency(latency);
        //save event log
        loggingService.save(logEvent);
        //response to the client with data
        Response.respondWithData(response, responseBody, responseData.getStatusCode());
    }

    //clean up resources
    public void destroy() {
    }
}