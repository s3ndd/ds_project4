package edu.cmu.andrew.project4task2webservice.util;

import edu.cmu.andrew.project4task2webservice.model.DeviceInfo;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class Request {

    /**
     * generate a request id
     *
     * @return a string request id
     */
    public static String generateRequestID() {
        return UUID.randomUUID().toString();
    }

    /**
     * fetch the request device information from the http request header
     *
     * @param request a HttpServletRequest request
     * @return the DeviceInfo object
     */
    public static DeviceInfo fetchRequestDevice(HttpServletRequest request) {

        return new DeviceInfo(request.getHeader("Manufacture"),
                request.getHeader("Brand"),
                request.getHeader("Model"),
                request.getHeader("AndroidVersion"));
    }
}
