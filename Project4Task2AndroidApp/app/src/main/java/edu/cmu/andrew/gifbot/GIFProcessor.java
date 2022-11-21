/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for processing the GIF search
 */
package edu.cmu.andrew.gifbot;

import android.app.Activity;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class GIFProcessor {
    // the backend server url for searching the gifs
    private final static String GIF_BOT_SERVICE_URL = "https://ds-project4-gifbot.herokuapp" +
            ".com/Project4Task2WebService-1.0-SNAPSHOT/api/v1/gif";

    // the main activity
    private GIFBotActivity gifBotActivity = null;

    // the search term
    private String searchTerm = null;

    private GIFsResponse giFsResponse = null;

    /**
     * search the gifs with the given searchTerm and set up the callback.
     * Search will run in a background task
     */
    public void search(String searchTerm, Activity activity, GIFBotActivity gifBotActivity) {
        // the main activity
        this.gifBotActivity = gifBotActivity;
        // update the search term
        this.searchTerm = searchTerm;
        // initialize the response
        giFsResponse = null;
        // run the search in a new thread
        new Thread(() -> {
            // search the gifs
            search(searchTerm);
            // set up the callback
            activity.runOnUiThread(() -> onPostExecute());
            // start the thread
        }).start();
    }

    /**
     * search the gifs with the given search term from the backend service
     */
    private void search(String searchTerm) {
        try {
            // get the response
            this.giFsResponse = get(GIF_BOT_SERVICE_URL, searchTerm);
            // System.out.println(this.giFsResponse.toString());
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

    /**
     * search the gifs from the backend service
     */
    private GIFsResponse get(String url, String searchTerm) throws IOException {
        // create a url builder
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        // add the query parameter to the url
        urlBuilder.addQueryParameter("search", searchTerm);
        // create a request builder
        Request.Builder requestBuilder = new Request.Builder();
        // build the http request
        Request request = requestBuilder.headers(attachDeviceInfoHeaders()).url(urlBuilder.toString()).build();
        // execute the http request and retrieve the response body
        ResponseBody responseBody = new OkHttpClient().newCall(request).execute().body();
        // convert the response body to String
        String responseBodyString = responseBody.string();
        // System.out.println("Raw response from Tenor:" + responseBodyString);
        // create an ObjectMapper for JSON deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        // deserialize the response
        GIFsResponse giFsResponse = objectMapper.readValue(responseBodyString, GIFsResponse.class);
        // System.out.println(giFsResponse);
        // return the response
        return giFsResponse;
    }

    /**
     * attach the required headers for statistic
     */
    private Headers attachDeviceInfoHeaders() {
        // create a header builder
        Headers.Builder builder = new Headers.Builder();
        // add the MANUFACTURER to header
        builder.add("Manufacture", android.os.Build.MANUFACTURER);
        // add the BRAND to header
        builder.add("Brand", android.os.Build.BRAND);
        // add the MODEL to header
        builder.add("Model", android.os.Build.MODEL);
        // add the RELEASE to header
        builder.add("AndroidVersion", android.os.Build.VERSION.RELEASE);
        // return the headers
        return builder.build();
    }

}

