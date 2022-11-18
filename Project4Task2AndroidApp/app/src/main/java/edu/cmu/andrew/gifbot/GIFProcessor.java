package edu.cmu.andrew.gifbot;

import android.app.Activity;
import com.bumptech.glide.RequestBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okhttp3.internal.http.HttpHeaders;

import java.io.IOException;

public class GIFProcessor {
    private final static String GIF_BOT_SERVICE_URL = "https://ds-project4-gifbot.herokuapp" +
            ".com/Project4Task2WebService-1.0-SNAPSHOT/api/v1/gif";
    private GIFBotActivity gifBotActivity = null;

    private String searchTerm = null;

    private GIFsResponse giFsResponse = null;

    public void search(String searchTerm, Activity activity, GIFBotActivity gifBotActivity) {
        this.gifBotActivity = gifBotActivity;
        this.searchTerm = searchTerm;
        giFsResponse = null;
        new Thread(() -> {
            search(searchTerm);
            activity.runOnUiThread(() -> onPostExecute());
        }).start();
    }

    private void search(String searchTerm) {
        try {
            this.giFsResponse = get(GIF_BOT_SERVICE_URL, searchTerm);
            System.out.println(this.giFsResponse.toString());
        } catch (IOException e) {
            this.giFsResponse = new GIFsResponse(500, "failed to handle the response");
        }
    }

    // onPostExecute( ) will run on the UI thread after the background
    //    thread completes.
    // Implement this method to suit your needs
    private void onPostExecute() {
        gifBotActivity.searchReady(giFsResponse);
    }

    private GIFsResponse get(String url, String searchTerm) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("search", searchTerm);
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.headers(attachDeviceInfoHeaders()).url(urlBuilder.toString()).build();
        ResponseBody responseBody = new OkHttpClient().newCall(request).execute().body();
        String responseBodyString = responseBody.string();
        System.out.println("Raw response from Tenor:" + responseBodyString);
        ObjectMapper objectMapper = new ObjectMapper();
        GIFsResponse giFsResponse = objectMapper.readValue(responseBodyString, GIFsResponse.class);
        System.out.println(giFsResponse);

        return giFsResponse;
    }

    private Headers attachDeviceInfoHeaders() {
        Headers.Builder builder = new Headers.Builder();
        builder.add("Manufacture", android.os.Build.MANUFACTURER);
        builder.add("Brand", android.os.Build.BRAND);
        builder.add("Model", android.os.Build.MODEL);
        builder.add("AndroidVersion", android.os.Build.VERSION.RELEASE);
        return builder.build();
    }

}

