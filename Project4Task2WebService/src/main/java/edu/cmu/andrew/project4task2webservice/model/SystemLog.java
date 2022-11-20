package edu.cmu.andrew.project4task2webservice.model;

public class SystemLog {
    private final String clientRequest;
    private final String clientResponse;
    private final String clientDevice;
    private final String externalAPIRequest;
    private final String externalAPIResponse;

    public SystemLog(String clientRequest, String clientResponse, String clientDevice, String externalAPIRequest,
                     String externalAPIResponse) {
        this.clientRequest = clientRequest;
        this.clientResponse = clientResponse;
        this.clientDevice = clientDevice;
        this.externalAPIRequest = externalAPIRequest;
        this.externalAPIResponse = externalAPIResponse;
    }

    public String getClientRequest() {
        return clientRequest;
    }

    public String getClientResponse() {
        return clientResponse;
    }

    public String getClientDevice() {
        return clientDevice;
    }

    public String getExternalAPIRequest() {
        return externalAPIRequest;
    }

    public String getExternalAPIResponse() {
        return externalAPIResponse;
    }
}
