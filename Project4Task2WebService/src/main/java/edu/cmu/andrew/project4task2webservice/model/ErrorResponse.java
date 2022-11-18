package edu.cmu.andrew.project4task2webservice.model;

public class ErrorResponse implements IResponse {

    private String message;

    private final int statusCode;

    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
