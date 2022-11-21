/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for GIFBotActivity
 */
package edu.cmu.andrew.gifbot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class GIFBotActivity extends AppCompatActivity {

    // define the context with the GIFBotActivity
    private final Context context = GIFBotActivity.this;

    // a variable to represent the GIFBotActivity
    private final GIFBotActivity me = this;

    /**
     * implement the onCreate method for the mobile application
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // call the parent's onCreate method
        super.onCreate(savedInstanceState);
        // set up the content view with the layout resource
        setContentView(R.layout.activity_gifbot);

        // define a reference to this object, which is used in the callback
        final GIFBotActivity gifBotActivity = this;

        // add listener to search button
        SearchView searchView = (SearchView) findViewById(R.id.searchView);

        // add listener to the searchView component
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // implement the onQueryTextSubmit to search gifs
            @Override
            public boolean onQueryTextSubmit(String searchTerm) {
                searchTerm = searchTerm.trim();
                if (searchTerm.length() == 0 || searchTerm.length() > 50) {
                    displayError(400, "invalid input");
                } else {
                    System.out.println("Search term" + searchTerm);
                    // define a GIFProcessor to handle the gif search
                    GIFProcessor gifProcessor = new GIFProcessor();
                    // call the search method to get the gifs
                    gifProcessor.search(searchTerm, me, gifBotActivity);
                }

                // close the input keyboard
                searchView.clearFocus();
                return true;
            }

            // implement the onQueryTextChange method which is not used in this application
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // find the ListView by id
        ListView listView = findViewById(R.id.gifListView);
        // define a new TextView
        TextView textView = new TextView(context);
        // set the TextView color to withe
        textView.setTextColor(Color.WHITE);
        // set the text size of the TextView
        textView.setTextSize(20);
        // set up the text of the Search Result view
        textView.setText("Search Result:");

        // add the TextView to the ListView
        listView.addHeaderView(textView);
    }

    /**
     * searchReady is a callback function to handle the gif response when it is ready
     */
    public void searchReady(GIFsResponse response) {
        // if the response status code is 200, it means the request is successful
        if (response.getStatusCode() == 200) {
            // search successfully
            displayGIFs(response.getGifs().toArray(new String[0]));
        } else {
            // error
            displayError(response.getStatusCode(), response.getMessage());
        }
    }


    /**
     * show the Gifs with the ListView
     */
    private void displayGIFs(String[] gifs) {
        // find the ListView by id
        ListView listView = findViewById(R.id.gifListView);
        // display the gifs
        listView.setAdapter(new GIFListAdapter(
                context,
                gifs
        ));
        // set the ListView visible
        listView.setVisibility(View.VISIBLE);
        // set the error message component invisible
        findViewById(R.id.errorMessage).setVisibility(View.INVISIBLE);
    }

    /**
     * show the error message
     */
    @SuppressLint("DefaultLocale")
    private void displayError(int statusCode, String message) {
        // find the ListView by id
        ListView listView = findViewById(R.id.gifListView);
        // set the ListView to be invisible
        listView.setVisibility(View.INVISIBLE);
        // find the error message component by id
        TextView errorView = findViewById(R.id.errorMessage);
        // set the component to be visible
        errorView.setVisibility(View.VISIBLE);
        // display the error message
        errorView.setText(String.format("Status Code: %d, Error Message: %s\n", statusCode, message));
    }
}