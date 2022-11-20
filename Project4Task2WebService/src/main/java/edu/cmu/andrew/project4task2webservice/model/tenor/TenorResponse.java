package edu.cmu.andrew.project4task2webservice.model.tenor;

import java.util.List;

public class TenorResponse {
    String next;
    List<TenorResult> results;

    public TenorResponse() {
    }

    public String getNext() {
        return this.next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<TenorResult> getResults() {
        return results;
    }

    public void setResults(List<TenorResult> results) {
        this.results = results;
    }
}

