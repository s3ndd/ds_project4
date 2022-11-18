package edu.cmu.andrew.project4task2webservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.andrew.project4task2webservice.model.IResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Response {
    public static String buildResponseBody(IResponse response) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(response);
    }

    public static void respondWithData(HttpServletResponse response, String responseBody, int statusCode) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(statusCode);
        out.print(responseBody);
        out.flush();
    }
}
