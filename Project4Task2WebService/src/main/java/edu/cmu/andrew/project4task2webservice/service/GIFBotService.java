/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for GIFBot service to get gifs through third party API.
 *
 */

package edu.cmu.andrew.project4task2webservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.andrew.project4task2webservice.model.*;
import edu.cmu.andrew.project4task2webservice.model.tenor.TenorMediaFormat;
import edu.cmu.andrew.project4task2webservice.model.tenor.TenorResponse;
import edu.cmu.andrew.project4task2webservice.model.tenor.TenorResult;
import jakarta.servlet.http.HttpServletResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GIFBotService {

    //Tenor API url base
    private final static String TENOR_BASE_URL = "https://tenor.googleapis.com";

    //Tenor API url path
    private final static String TENOR_PATH = "/v2/search";

    //Tenor API key
    private final static String TENOR_API_KEY = "AIzaSyAJCaxc_oKC0MjCteA3hczXMe1GpUyayuw";

    //Tenor API default search gifs limit
    private final String DEFAULT_LIMIT = "10";

    //http client
    private static OkHttpClient httpClient = null;

    //logging service to save log event
    private final LoggingService loggingService;

    //constructor with logging service input
    public GIFBotService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    //get http client
    synchronized private static OkHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient();
        }
        return httpClient;
    }

    //search GIF results with input search text
    public IResponse search(String requestID, String searchText) {
        try {
            //check if search text is null or over 50 characters
            if (searchText == null || searchText.length() == 0 || searchText.length() > 50) {
                //return error response and message
                return new ErrorResponse("invalid input parameter", HttpServletResponse.SC_BAD_REQUEST);
            }
            //build tenor search url
            String url = buildTenorSearchURL(searchText);
            //build request
            Request request = (new Request.Builder()).url(url).build();
            //save system service start time
            long start = System.currentTimeMillis();
            //create event log with ID
            LogEvent logEvent = new LogEvent(requestID);
            //set Tenor request information to event log
            logEvent.setTenorRequestInfo(new TenorRequestInfo(TENOR_BASE_URL, url.replace(TENOR_BASE_URL, "")));
            //save log event
            this.loggingService.save(logEvent);
            //get response from API
            Response response = getHttpClient().newCall(request).execute();
            //save system service latency
            long latency = System.currentTimeMillis() - start;
            //response message body
            ResponseBody responseBody = response.body();
            //response body content
            String responseBodyString = responseBody.string();
            //set response info to event log
            logEvent.setTenorResponseInfo(new TenorResponseInfo(responseBodyString, response.code()));
            //set latency to event log
            logEvent.setTenorAPILatency(latency);
            //save event log
            loggingService.save(logEvent);
            //create gif response object
            IResponse giFsResponse;
            //check if response code is 200 or 202, api call returns success
            if (response.code() == HttpServletResponse.SC_OK || response.code() == HttpServletResponse.SC_ACCEPTED) {
                //create object mapper
                ObjectMapper objectMapper = new ObjectMapper();
                //parse response content into a Java object
                TenorResponse tenorResponse = objectMapper.readValue(responseBodyString, TenorResponse.class);
                //build gif response
                giFsResponse = buildGIFsResponse(tenorResponse);
            } else {
                //api call returns error
                giFsResponse = new ErrorResponse("failed to search the gifs from the 3rd part", response.code());
            }
            return giFsResponse;
            //catch exception
        } catch (IOException e) {
            return new ErrorResponse(e.getMessage(), HttpServletResponse.SC_BAD_GATEWAY);
        }
    }

    //build tenor search url
    private String buildTenorSearchURL(String searchText) {
        //http url builder
        HttpUrl.Builder urlBuilder = HttpUrl.parse(TENOR_BASE_URL + TENOR_PATH).newBuilder();
        //build tenor search url
        urlBuilder.addQueryParameter("key", TENOR_API_KEY)
                .addQueryParameter("q", searchText)
                .addQueryParameter("client_key", "ds_project4_gifbot")
                .addQueryParameter("media_filter", "tinygif")
                .addQueryParameter("random", "false")
                .addQueryParameter("limit", DEFAULT_LIMIT);
        //return tenor search url
        return urlBuilder.build().toString();
    }

    //build gif response
    private static GIFsResponse buildGIFsResponse(TenorResponse tenorResponse) {
        //list to store gifs
        List<String> gifs = new ArrayList<>();
        for (TenorResult result : tenorResponse.getResults()) {
            //create TenorMediaFormat object from tenor result
            TenorMediaFormat mediaFormat = result.getTenorMediaFormats();
            //check if MediaFormat and tenor tiny gif are not null
            if (mediaFormat != null && mediaFormat.getTenorTinyGif() != null) {
                //add gif url to the list
                gifs.add(mediaFormat.getTenorTinyGif().getUrl());
            }
        }
        //return gifs list and response code
        return new GIFsResponse(gifs, HttpServletResponse.SC_OK);
    }
}
