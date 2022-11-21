/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for GIFsResponse
 */
package edu.cmu.andrew.gifbot;

import java.util.List;

public class GIFsResponse {
    // define a variable to save a list of gif urls
    private List<String> gifs;

    // the http response status code
    private int statusCode;

    // the error message from the server side
    private String message;

    // create a GIFsResponse object
    public GIFsResponse() {
    }

    // create a GIFsResponse object with the given parameters
    public GIFsResponse(List<String> gifs, int statusCode) {
        this.gifs = gifs;
        this.statusCode = statusCode;
        this.message = null;
    }

    // create a GIFsResponse object with the given parameters
    public GIFsResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.gifs = null;
    }

    // a getter to return a list of gif urls
    public List<String> getGifs() {
        return gifs;
    }

    // a setter to set the gifs with the given parameter
    public void setGifs(List<String> gifs) {
        this.gifs = gifs;
    }

    // a getter to return the status code
    public int getStatusCode() {
        return statusCode;
    }

    // a setter to set the status code
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    // a getter to return the error message
    public String getMessage() {
        return message;
    }

    // a setter to set the error message with the given parameter
    public void setMessage(String message) {
        this.message = message;
    }
    
    // override toString for print out
    @Override
    public String toString() {
        return "GIFsResponse{" +
                "gifs=" + gifs +
                ", statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
}
