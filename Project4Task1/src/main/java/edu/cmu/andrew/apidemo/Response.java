package edu.cmu.andrew.apidemo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Response {
    String next;
    List<TenorResponse> results;

    public Response() {
    }

    public String getNext() {
        return this.next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<TenorResponse> getResults() {
        return results;
    }

    public void setResults(List<TenorResponse> results) {
        this.results = results;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class TenorResponse {
    String id;
    @JsonProperty("media_formats")
    MediaFormat mediaFormats;
    @JsonProperty("content_description")
    String contentDescription;

    public TenorResponse() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("media_formats")
    public MediaFormat getMediaFormat() {
        return this.mediaFormats;
    }

    @JsonProperty("media_formats")
    public void setMediaFormat(MediaFormat mediaFormats) {
        this.mediaFormats = mediaFormats;
    }

    @JsonProperty("content_description")
    public String getContentDescription() {
        return this.contentDescription;
    }

    @JsonProperty("content_description")
    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class MediaFormat {
    @JsonProperty("tinygif")
    TinyGif tinyGif;

    public MediaFormat() {
    }

    public TinyGif getTinyGif() {
        return this.tinyGif;
    }

    public void setTinyGif(TinyGif tinyGif) {
        this.tinyGif = tinyGif;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class TinyGif {
    String url;
    Integer[] dims;

    public TinyGif() {
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
}