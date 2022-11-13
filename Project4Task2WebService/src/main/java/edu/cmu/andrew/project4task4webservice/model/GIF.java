package edu.cmu.andrew.project4task4webservice.model;

public class GIF {
    private String url;

    private Integer[] dims;

    public GIF(String url, Integer[] dims) {
        this.url = url;
        this.dims = dims;
    }

    public String getUrl() {
        return url;
    }

    public Integer[] getDims() {
        return dims;
    }
}
