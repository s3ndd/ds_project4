package edu.cmu.andrew.project4task4webservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.andrew.project4task4webservice.model.ErrorResponse;
import edu.cmu.andrew.project4task4webservice.model.GIF;
import edu.cmu.andrew.project4task4webservice.model.GIFsResponse;
import edu.cmu.andrew.project4task4webservice.model.IResponse;
import edu.cmu.andrew.project4task4webservice.model.tenor.TenorMediaFormat;
import edu.cmu.andrew.project4task4webservice.model.tenor.TenorResponse;
import edu.cmu.andrew.project4task4webservice.model.tenor.TenorResult;
import jakarta.servlet.http.HttpServletResponse;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GIFBotService {
    private final static String TENOR_SEARCH_URL = "https://tenor.googleapis.com/v2/search";
    private final static String TENOR_API_KEY = "AIzaSyAJCaxc_oKC0MjCteA3hczXMe1GpUyayuw";
    private final String DEFAULT_LIMIT = "10";

    private static OkHttpClient httpClient = null;

    public GIFBotService() {
    }

    synchronized private static OkHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient();
        }
        return httpClient;
    }

    public IResponse search(String searchText) {
        try {
            if (searchText == null || searchText.length() == 0 || searchText.length() > 50) {
                return new ErrorResponse("invalid input parameter", HttpServletResponse.SC_BAD_REQUEST);
            }
            String url = buildTenorSearchURL(searchText);
            Request request = (new Request.Builder()).url(url).build();
            ResponseBody responseBody = getHttpClient().newCall(request).execute().body();
            String responseBodyString = responseBody.string();
            System.out.println("Raw response from Tenor:" + responseBodyString);
            ObjectMapper objectMapper = new ObjectMapper();
            TenorResponse tenorResponse = objectMapper.readValue(responseBodyString, TenorResponse.class);
            System.out.println(tenorResponse);
            return buildGIFsResponse(tenorResponse);
        } catch (IOException e) {
            return new ErrorResponse(e.getMessage(), HttpServletResponse.SC_BAD_GATEWAY);
        }
    }

    private String buildTenorSearchURL(String searchText) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(TENOR_SEARCH_URL).newBuilder();
        urlBuilder.addQueryParameter("key", "AIzaSyAJCaxc_oKC0MjCteA3hczXMe1GpUyayuw")
                .addQueryParameter("q", searchText)
                .addQueryParameter("client_key", "ds_project4_gifbot")
                .addQueryParameter("media_filter", "tinygif")
                .addQueryParameter("random", "false")
                .addQueryParameter("limit", DEFAULT_LIMIT);
        return urlBuilder.build().toString();
    }

    private static GIFsResponse buildGIFsResponse(TenorResponse tenorResponse) {
        List<GIF> gifs = new ArrayList<>();
        Set<String> tags = new HashSet<>();
        for (TenorResult result : tenorResponse.getResults()) {
            TenorMediaFormat mediaFormat = result.getTenorMediaFormats();
            if (mediaFormat != null && mediaFormat.getTenorTinyGif() != null) {
                gifs.add(new GIF(mediaFormat.getTenorTinyGif().getUrl(), mediaFormat.getTenorTinyGif().getDims()));
            }
            List<String> gifTags = result.getTags();
            if (gifTags != null) {
                tags.addAll(gifTags);
            }
        }

        return new GIFsResponse(gifs, tags, HttpServletResponse.SC_OK);
    }
}
