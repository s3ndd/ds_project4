/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for response in servlet.
 */

package edu.cmu.andrew.project4task2webservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.andrew.project4task2webservice.model.IResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Response {
    // get response body and map to string content
    public static String buildResponseBody(IResponse response) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(response);
    }

    // response to the client with data
    public static void respondWithData(HttpServletResponse response, String responseBody, int statusCode) throws IOException {
        // create PrintWriter object that can send character text to the client
        PrintWriter out = response.getWriter();
        // set response content type
        response.setContentType("application/json");
        // set response character encoding
        response.setCharacterEncoding("UTF-8");
        // set response status code
        response.setStatus(statusCode);
        // write responseBody on the stream
        out.print(responseBody);
        // clear the stream of any element
        out.flush();
    }
}
