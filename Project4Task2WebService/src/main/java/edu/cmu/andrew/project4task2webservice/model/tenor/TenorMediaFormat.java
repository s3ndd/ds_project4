/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for Tenor media format.
 *
 */

package edu.cmu.andrew.project4task2webservice.model.tenor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TenorMediaFormat {
    //tenor tiny gif
    @JsonProperty("tinygif")
    TenorTinyGif tenorTinyGif;

    //constructor of TenorMediaFormat
    public TenorMediaFormat() {
    }

    //get tenor tiny gif
    public TenorTinyGif getTenorTinyGif() {
        return tenorTinyGif;
    }

    //set tenor tiny gif
    public void setTenorTinyGif(TenorTinyGif tenorTinyGif) {
        this.tenorTinyGif = tenorTinyGif;
    }

    //override toString for print out
    @Override
    public String toString() {
        return "MediaFormat{" +
                "tinyGif=" + tenorTinyGif +
                '}';
    }
}
