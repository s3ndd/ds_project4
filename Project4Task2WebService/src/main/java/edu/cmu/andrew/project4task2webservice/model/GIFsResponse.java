/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for GIFs API response content
 * It implements IResponse interface.
 */

package edu.cmu.andrew.project4task2webservice.model;

import java.util.List;

public class GIFsResponse implements IResponse {
    // string list to store GIFs Search Result
    private List<String> gifs;
    // status code of GIFs API response
    private final int statusCode;

    // constructor of GIFsResponse with gifs list and status code input
    public GIFsResponse(List<String> gifs, int statusCode) {
        this.gifs = gifs;
        this.statusCode = statusCode;
    }

    // get string list of GIFs Search Result
    public List<String> getGifs() {
        return gifs;
    }

    // set string list of GIFs Search Result
    public void setGifs(List<String> gifs) {
        this.gifs = gifs;
    }

    // get status code of GIFs API response
    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
