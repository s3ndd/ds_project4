package edu.cmu.andrew.gifbot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class GIFBotActivity extends AppCompatActivity {

    private final Context context = GIFBotActivity.this;
    private final GIFBotActivity me = this;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifbot);

        final GIFBotActivity gifBotActivity = this;

        // add listener to search button
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String searchTerm) {
                System.out.println("Search term" + searchTerm);
                GIFProcessor gifProcessor = new GIFProcessor();
                gifProcessor.search(searchTerm, me, gifBotActivity);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        ListView listView = findViewById(R.id.gifListView);
        TextView textView = new TextView(context);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(20);
        textView.setText("Search Result:");

        listView.addHeaderView(textView);


    }

    public void searchReady(GIFsResponse response) {
        if (response.getStatusCode() == 200) {
            // search successfully
            displayGIFs(response.getGifs().toArray(new String[0]));
        } else {
            // error
            displayError(response.getStatusCode(), response.getMessage());
        }
    }


    private void displayGIFs(String[] gifs) {
        ListView listView = findViewById(R.id.gifListView);
        listView.setAdapter(new GIFListAdapter(
                context,
                gifs
        ));
        listView.setVisibility(View.VISIBLE);
        findViewById(R.id.errorMessage).setVisibility(View.INVISIBLE);
    }

    private void displayError(int statusCode, String message) {
        ListView listView = findViewById(R.id.gifListView);
        listView.setVisibility(View.INVISIBLE);
        TextView errorView = findViewById(R.id.errorMessage);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(String.format("Status Code: %d, Error Message: %s\n", statusCode, message));
    }
}