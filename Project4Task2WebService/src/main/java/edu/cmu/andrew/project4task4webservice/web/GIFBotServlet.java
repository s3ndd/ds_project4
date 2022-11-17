package edu.cmu.andrew.project4task4webservice.web;

import edu.cmu.andrew.project4task4webservice.model.IResponse;
import edu.cmu.andrew.project4task4webservice.service.GIFBotService;
import edu.cmu.andrew.project4task4webservice.util.Response;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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