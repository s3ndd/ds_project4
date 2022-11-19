/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for Tenor response with list of tenor results.
 *
 */

package edu.cmu.andrew.project4task2webservice.model.tenor;

import java.util.List;

public class TenorResponse {
    //next content
    String next;
    //list to store tenor results.
    List<TenorResult> results;

    //constructor of TenorResponse
    public TenorResponse() {
    }

    //get next content
    public String getNext() {
        return this.next;
    }

    //set next content
    public void setNext(String next) {
        this.next = next;
    }

    //get list of tenor results.
    public List<TenorResult> getResults() {
        return results;
    }

    //set list of tenor results.
    public void setResults(List<TenorResult> results) {
        this.results = results;
    }

    //override toString for print out
    @Override
    public String toString() {
        return "TenorResponse{" +
                "next='" + next + '\'' +
                ", results=" + results +
                '}';
    }
}

