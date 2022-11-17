package edu.cmu.andrew.project4task4webservice.model;

import java.util.List;
import java.util.Set;

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
