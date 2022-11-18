package edu.cmu.andrew.project4task2webservice.model.tenor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public
class TenorResult {
    String id;
    @JsonProperty("media_formats")
    TenorMediaFormat tenorMediaFormats;
    @JsonProperty("content_description")
    String contentDescription;

    List<String> tags;

    public TenorResult() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("content_description")
    public String getContentDescription() {
        return this.contentDescription;
    }

    @JsonProperty("content_description")
    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("media_formats")
    public TenorMediaFormat getTenorMediaFormats() {
        return tenorMediaFormats;
    }

    @JsonProperty("media_formats")
    public void setTenorMediaFormats(TenorMediaFormat tenorMediaFormats) {
        this.tenorMediaFormats = tenorMediaFormats;
    }
}
