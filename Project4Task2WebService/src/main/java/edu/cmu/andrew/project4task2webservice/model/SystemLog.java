/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 */

package edu.cmu.andrew.project4task2webservice.model;

public class SystemLog {
    // the client request string
    private final String clientRequest;

    // the client response string
    private final String clientResponse;

    // the client device string
    private final String clientDevice;

    // the api request string
    private final String externalAPIRequest;

    // the api response string
    private final String externalAPIResponse;

    // create the SystemLog with the given parameter
    public SystemLog(String clientRequest, String clientResponse, String clientDevice, String externalAPIRequest,
                     String externalAPIResponse) {
        this.clientRequest = clientRequest;
        this.clientResponse = clientResponse;
        this.clientDevice = clientDevice;
        this.externalAPIRequest = externalAPIRequest;
        this.externalAPIResponse = externalAPIResponse;
    }

    // a getter to return the client request
    public String getClientRequest() {
        return clientRequest;
    }

    // a getter to return the client response
    public String getClientResponse() {
        return clientResponse;
    }

    // a getter to return the client device
    public String getClientDevice() {
        return clientDevice;
    }

    // a getter to return the api request
    public String getExternalAPIRequest() {
        return externalAPIRequest;
    }

    // a getter to return the api response
    public String getExternalAPIResponse() {
        return externalAPIResponse;
    }
}
