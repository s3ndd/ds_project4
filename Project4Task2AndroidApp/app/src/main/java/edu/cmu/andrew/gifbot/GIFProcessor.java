package edu.cmu.andrew.gifbot;

import android.app.Activity;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

public class GIFProcessor {
    private final static String GIF_BOT_SERVICE_URL = " https://459c-74-109-237-3.ngrok.io/Project4Task4WebService-1" +
            ".0-SNAPSHOT/api/v1/gif";
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
        Request request = (new Request.Builder()).url(urlBuilder.toString()).build();
        ResponseBody responseBody = new OkHttpClient().newCall(request).execute().body();
        String responseBodyString = responseBody.string();
        System.out.println("Raw response from Tenor:" + responseBodyString);
        ObjectMapper objectMapper = new ObjectMapper();
        GIFsResponse giFsResponse = objectMapper.readValue(responseBodyString, GIFsResponse.class);
        System.out.println(giFsResponse);

        return giFsResponse;
    }

}

