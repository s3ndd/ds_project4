package edu.cmu.andrew.project4task4webservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.andrew.project4task4webservice.model.IResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Response {
    public static void respondWithData(HttpServletResponse response, IResponse data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String responseJson = mapper.writeValueAsString(data);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(data.getStatusCode());
        out.print(responseJson);
        out.flush();
    }
}
