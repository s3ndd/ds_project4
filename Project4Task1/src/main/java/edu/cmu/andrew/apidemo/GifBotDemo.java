package edu.cmu.andrew.apidemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Scanner;

public class GifBotDemo {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to GifBot \ud83e\udd16");
        System.out.println("Search all the Gifs and Stickers(Q to exit): ");
        Scanner in = new Scanner(System.in);
        String searchText = in.nextLine();
        if (searchText.equals("Q")) {
            System.exit(0);
        } else {
            Response response = search(searchText);
            ObjectMapper mapper = new ObjectMapper();
            String responseJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            System.out.println("Parsed Json Response(with useful fields):\n " + responseJson);
        }

    }

    public static Response search(String searchText) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://tenor.googleapis.com/v2/search").newBuilder();
        urlBuilder.addQueryParameter("key", "AIzaSyAJCaxc_oKC0MjCteA3hczXMe1GpUyayuw")
                .addQueryParameter("q", searchText)
                .addQueryParameter("client_key", "ds_project4_gifbot")
                .addQueryParameter("media_filter", "tinygif")
                .addQueryParameter("random", "true")
                .addQueryParameter("limit", "1");
        String url = urlBuilder.build().toString();
        Request request = (new Request.Builder()).url(url).build();
        OkHttpClient client = new OkHttpClient();
        ResponseBody responseBody = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBodyString = responseBody.string();
        System.out.printf("Raw Response Body: \n%s\n", responseBodyString);
        return (Response) objectMapper.readValue(responseBodyString, Response.class);
    }
}
