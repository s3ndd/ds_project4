package edu.cmu.andrew.project4task2webservice.model;

import java.util.List;

public class GIFsResponse implements IResponse {
    private List<String> gifs;
    private final int statusCode;


    public GIFsResponse(List<String> gifs, int statusCode) {
        this.gifs = gifs;
        this.statusCode = statusCode;
    }

    public List<String> getGifs() {
        return gifs;
    }

    public void setGifs(List<String> gifs) {
        this.gifs = gifs;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
