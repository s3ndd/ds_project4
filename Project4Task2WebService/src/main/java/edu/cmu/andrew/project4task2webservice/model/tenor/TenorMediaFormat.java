package edu.cmu.andrew.project4task2webservice.model.tenor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TenorMediaFormat {
    @JsonProperty("tinygif")
    TenorTinyGif tenorTinyGif;


    public TenorMediaFormat() {
    }

    public TenorTinyGif getTenorTinyGif() {
        return tenorTinyGif;
    }

    public void setTenorTinyGif(TenorTinyGif tenorTinyGif) {
        this.tenorTinyGif = tenorTinyGif;
    }
}
