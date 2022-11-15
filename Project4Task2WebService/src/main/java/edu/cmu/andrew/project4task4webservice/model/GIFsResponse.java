package edu.cmu.andrew.project4task4webservice.model;

import java.util.List;
import java.util.Set;

public class GIFsResponse implements IResponse {
    private List<GIF> gifs;

    private Set<String> tags;

    private final int statusCode;




    public GIFsResponse(List<GIF> gifs, Set<String> tags, int statusCode) {
        this.gifs = gifs;
        this.tags = tags;
        this.statusCode = statusCode;
    }


    public List<GIF> getGifs() {
        return gifs;
    }

    public void setGifs(List<GIF> gifs) {
        this.gifs = gifs;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
