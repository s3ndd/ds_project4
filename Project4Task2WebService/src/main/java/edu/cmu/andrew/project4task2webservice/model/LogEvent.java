package edu.cmu.andrew.project4task2webservice.model;


public class LogEvent {
    private String requestID;

    private ClientRequestInfo clientRequestInfo;

    private DeviceInfo requestDeviceInfo;

    private ClientResponseInfo clientResponseInfo;

    private Long serviceLatency;

    private TenorRequestInfo tenorRequestInfo;

    private TenorResponseInfo tenorResponseInfo;

    private Long tenorAPILatency;

    public LogEvent() {
    }

    public LogEvent(String requestID) {
        this.requestID = requestID;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public ClientRequestInfo getClientRequestInfo() {
        return clientRequestInfo;
    }

    public void setClientRequestInfo(ClientRequestInfo clientRequestInfo) {
        this.clientRequestInfo = clientRequestInfo;
    }

    public DeviceInfo getRequestDeviceInfo() {
        return requestDeviceInfo;
    }

    public void setRequestDeviceInfo(DeviceInfo requestDeviceInfo) {
        this.requestDeviceInfo = requestDeviceInfo;
    }

    public ClientResponseInfo getClientResponseInfo() {
        return clientResponseInfo;
    }

    public void setClientResponseInfo(ClientResponseInfo clientResponseInfo) {
        this.clientResponseInfo = clientResponseInfo;
    }

    public Long getServiceLatency() {
        return serviceLatency;
    }

    public void setServiceLatency(Long serviceLatency) {
        this.serviceLatency = serviceLatency;
    }

    public TenorRequestInfo getTenorRequestInfo() {
        return tenorRequestInfo;
    }

    public void setTenorRequestInfo(TenorRequestInfo tenorRequestInfo) {
        this.tenorRequestInfo = tenorRequestInfo;
    }

    public TenorResponseInfo getTenorResponseInfo() {
        return tenorResponseInfo;
    }

    public void setTenorResponseInfo(TenorResponseInfo tenorResponseInfo) {
        this.tenorResponseInfo = tenorResponseInfo;
    }

    public Long getTenorAPILatency() {
        return tenorAPILatency;
    }

    public void setTenorAPILatency(Long tenorAPILatency) {
        this.tenorAPILatency = tenorAPILatency;
    }

}
