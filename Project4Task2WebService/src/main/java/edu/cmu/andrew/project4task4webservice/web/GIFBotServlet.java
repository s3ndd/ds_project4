package edu.cmu.andrew.project4task4webservice.web;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.util.StringUtils;
import edu.cmu.andrew.project4task4webservice.model.GIFsResponse;
import edu.cmu.andrew.project4task4webservice.model.IResponse;
import edu.cmu.andrew.project4task4webservice.service.GIFBotService;
import edu.cmu.andrew.project4task4webservice.util.Response;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GIFBot", value = "/api/v1/gif")
public class GIFBotServlet extends HttpServlet {
    private GIFBotService gifBotService;

    public void init() {
        gifBotService = new GIFBotService();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getHeader("User-Agent"));
        String searchText = request.getParameter("search");
        IResponse responseData = gifBotService.search(searchText);
        Response.respondWithData(response, responseData);
    }

    public void destroy() {
    }
}