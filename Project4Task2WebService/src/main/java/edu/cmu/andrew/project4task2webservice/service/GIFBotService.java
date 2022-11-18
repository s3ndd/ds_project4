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
    private final static String TENOR_BASE_URL = "https://tenor.googleapis.com";
    private final static String TENOR_PATH = "/v2/search";
    private final static String TENOR_API_KEY = "AIzaSyAJCaxc_oKC0MjCteA3hczXMe1GpUyayuw";
    private final String DEFAULT_LIMIT = "10";

    private static OkHttpClient httpClient = null;

    private final LoggingService loggingService;

    public GIFBotService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    synchronized private static OkHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient();
        }
        return httpClient;
    }

    public IResponse search(String requestID, String searchText) {
        try {
            if (searchText == null || searchText.length() == 0 || searchText.length() > 50) {
                return new ErrorResponse("invalid input parameter", HttpServletResponse.SC_BAD_REQUEST);
            }
            String url = buildTenorSearchURL(searchText);
            Request request = (new Request.Builder()).url(url).build();
            long start = System.currentTimeMillis();
            LogEvent logEvent = new LogEvent(requestID);
            logEvent.setTenorRequestInfo(new TenorRequestInfo(TENOR_BASE_URL, url.replace(TENOR_BASE_URL, "")));
            this.loggingService.save(logEvent);
            Response response = getHttpClient().newCall(request).execute();
            long latency = System.currentTimeMillis() - start;
            ResponseBody responseBody = response.body();
            String responseBodyString = responseBody.string();
            logEvent.setTenorResponseInfo(new TenorResponseInfo(responseBodyString, response.code()));
            logEvent.setTenorAPILatency(latency);
            loggingService.save(logEvent);
            IResponse giFsResponse;
            if (response.code() == HttpServletResponse.SC_OK || response.code() == HttpServletResponse.SC_ACCEPTED) {
                // api call returns success
                ObjectMapper objectMapper = new ObjectMapper();
                TenorResponse tenorResponse = objectMapper.readValue(responseBodyString, TenorResponse.class);
                giFsResponse = buildGIFsResponse(tenorResponse);
            } else {
                // api call returns error
                giFsResponse = new ErrorResponse("failed to search the gifs from the 3rd part", response.code());
            }

            return giFsResponse;
        } catch (IOException e) {
            return new ErrorResponse(e.getMessage(), HttpServletResponse.SC_BAD_GATEWAY);
        }
    }

    private String buildTenorSearchURL(String searchText) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(TENOR_BASE_URL + TENOR_PATH).newBuilder();
        urlBuilder.addQueryParameter("key", TENOR_API_KEY)
                .addQueryParameter("q", searchText)
                .addQueryParameter("client_key", "ds_project4_gifbot")
                .addQueryParameter("media_filter", "tinygif")
                .addQueryParameter("random", "false")
                .addQueryParameter("limit", DEFAULT_LIMIT);

        return urlBuilder.build().toString();
    }

    private static GIFsResponse buildGIFsResponse(TenorResponse tenorResponse) {
        List<String> gifs = new ArrayList<>();
        for (TenorResult result : tenorResponse.getResults()) {
            TenorMediaFormat mediaFormat = result.getTenorMediaFormats();
            if (mediaFormat != null && mediaFormat.getTenorTinyGif() != null) {
                gifs.add(mediaFormat.getTenorTinyGif().getUrl());
            }
        }

        return new GIFsResponse(gifs, HttpServletResponse.SC_OK);
    }
}
