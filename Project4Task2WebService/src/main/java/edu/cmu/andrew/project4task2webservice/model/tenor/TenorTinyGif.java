package edu.cmu.andrew.project4task2webservice.model.tenor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TenorTinyGif {
    String url;
    Integer[] dims;

    public TenorTinyGif() {
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer[] getDims() {
        return this.dims;
    }

    public void setDims(Integer[] dims) {
        this.dims = dims;
    }

    @Override
    public String toString() {
        return "TinyGif{" +
                "url='" + url + '\'' +
                ", dims=" + Arrays.toString(dims) +
                '}';
    }
}
