/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for log event.
 *
 */

package edu.cmu.andrew.project4task2webservice.model;

public class LogEvent {
    //request identification
    private String requestID;

    //request information from client
    private ClientRequestInfo clientRequestInfo;

    //device information
    private DeviceInfo requestDeviceInfo;

    //response information from client
    private ClientResponseInfo clientResponseInfo;

    //system service latency
    private Long serviceLatency;

    //Tenor API request information
    private TenorRequestInfo tenorRequestInfo;

    //Tenor API response information
    private TenorResponseInfo tenorResponseInfo;

    //API service latency
    private Long tenorAPILatency;

    //constructor of LogEvent
    public LogEvent() {
    }

    //constructor of LogEvent with requestID input
    public LogEvent(String requestID) {
        this.requestID = requestID;
    }

    //get request identification
    public String getRequestID() {
        return requestID;
    }

    //set request identification
    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    //get request information from client
    public ClientRequestInfo getClientRequestInfo() {
        return clientRequestInfo;
    }

    //set request information from client
    public void setClientRequestInfo(ClientRequestInfo clientRequestInfo) {
        this.clientRequestInfo = clientRequestInfo;
    }

    //get device information from the request
    public DeviceInfo getRequestDeviceInfo() {
        return requestDeviceInfo;
    }

    //set device information from the request
    public void setRequestDeviceInfo(DeviceInfo requestDeviceInfo) {
        this.requestDeviceInfo = requestDeviceInfo;
    }

    //get response information from client
    public ClientResponseInfo getClientResponseInfo() {
        return clientResponseInfo;
    }

    //set response information from client
    public void setClientResponseInfo(ClientResponseInfo clientResponseInfo) {
        this.clientResponseInfo = clientResponseInfo;
    }

    //get system service latency
    public Long getServiceLatency() {
        return serviceLatency;
    }

    //set system service latency
    public void setServiceLatency(Long serviceLatency) {
        this.serviceLatency = serviceLatency;
    }

    //get Tenor API request information
    public TenorRequestInfo getTenorRequestInfo() {
        return tenorRequestInfo;
    }

    //set Tenor API request information
    public void setTenorRequestInfo(TenorRequestInfo tenorRequestInfo) {
        this.tenorRequestInfo = tenorRequestInfo;
    }

    //get Tenor API response information
    public TenorResponseInfo getTenorResponseInfo() {
        return tenorResponseInfo;
    }

    //set Tenor API response information
    public void setTenorResponseInfo(TenorResponseInfo tenorResponseInfo) {
        this.tenorResponseInfo = tenorResponseInfo;
    }

    //get Tenor API service latency
    public Long getTenorAPILatency() {
        return tenorAPILatency;
    }

    //set Tenor API service latency
    public void setTenorAPILatency(Long tenorAPILatency) {
        this.tenorAPILatency = tenorAPILatency;
    }

    //override toString for print out
    @Override
    public String toString() {
        return "LogEvent{" +
                "requestID='" + requestID + '\'' +
                ", clientRequestInfo=" + clientRequestInfo +
                ", requestDeviceInfo=" + requestDeviceInfo +
                ", clientResponseInfo=" + clientResponseInfo +
                ", serviceLatency=" + serviceLatency +
                ", tenorRequestInfo=" + tenorRequestInfo +
                ", tenorResponseInfo=" + tenorResponseInfo +
                ", tenorAPILatency=" + tenorAPILatency +
                '}';
    }
}
