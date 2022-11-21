/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for error message of response.
 * It implements IResponse interface.
 */

package edu.cmu.andrew.project4task2webservice.model;

public class ErrorResponse implements IResponse {

    // content of error message
    private String message;

    // status code of error message
    private final int statusCode;

    // constructor of ErrorResponse with error message and status code input
    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    // get error message content
    public String getMessage() {
        return message;
    }

    // set error message content
    public void setMessage(String message) {
        this.message = message;
    }

    // get status code of error message
    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
