/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for tenor result with format, content and tags.
 *
 */

package edu.cmu.andrew.project4task2webservice.model.tenor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public
class TenorResult {
    //identification
    String id;
    //Tenor media format
    @JsonProperty("media_formats")
    TenorMediaFormat tenorMediaFormats;
    //tenor result content
    @JsonProperty("content_description")
    String contentDescription;

    //tenor result tags
    List<String> tags;

    //constructor of TenorResult
    public TenorResult() {
    }

    //get identification
    public String getId() {
        return this.id;
    }

    //set identification
    public void setId(String id) {
        this.id = id;
    }

    //get tenor result content
    @JsonProperty("content_description")
    public String getContentDescription() {
        return this.contentDescription;
    }

    //set tenor result content
    @JsonProperty("content_description")
    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    //get tenor result tags
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    //set tenor result tags
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    //get tenor media format
    @JsonProperty("media_formats")
    public TenorMediaFormat getTenorMediaFormats() {
        return tenorMediaFormats;
    }

    //set tenor media format
    @JsonProperty("media_formats")
    public void setTenorMediaFormats(TenorMediaFormat tenorMediaFormats) {
        this.tenorMediaFormats = tenorMediaFormats;
    }
}
