/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for tenor tiny gif with url and dimension.
 *
 */

package edu.cmu.andrew.project4task2webservice.model.tenor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TenorTinyGif {
    //tenor tiny gif url
    String url;
    //tenor tiny gif dimension
    Integer[] dims;

    //constructor of TenorTinyGif
    public TenorTinyGif() {
    }

    //get tenor tiny gif url
    public String getUrl() {
        return this.url;
    }

    //set tenor tiny gif url
    public void setUrl(String url) {
        this.url = url;
    }

    //get tenor tiny gif dimension
    public Integer[] getDims() {
        return this.dims;
    }

    //set tenor tiny gif dimension
    public void setDims(Integer[] dims) {
        this.dims = dims;
    }

    //override toString for print out
    @Override
    public String toString() {
        return "TinyGif{" +
                "url='" + url + '\'' +
                ", dims=" + Arrays.toString(dims) +
                '}';
    }
}
