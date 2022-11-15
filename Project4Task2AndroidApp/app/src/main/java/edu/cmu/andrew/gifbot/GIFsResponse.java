package edu.cmu.andrew.gifbot;

import java.util.List;

public class GIFsResponse {
    private List<String> gifs;

    private int statusCode;

    private String message;

    public GIFsResponse() {
    }

    public GIFsResponse(List<String> gifs, int statusCode) {
        this.gifs = gifs;
        this.statusCode = statusCode;
        this.message = null;
    }

    public GIFsResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.gifs = null;
    }

    public List<String> getGifs() {
        return gifs;
    }

    public void setGifs(List<String> gifs) {
        this.gifs = gifs;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GIFsResponse{" +
                "gifs=" + gifs +
                ", statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
}
