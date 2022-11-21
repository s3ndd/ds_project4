/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for GIFListAdapter
 */
package edu.cmu.andrew.gifbot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

/**
 * GIFListAdapter is used to display the gifs in the ListView
 * Reference: https://github.com/bumptech/glide
 */
public class GIFListAdapter extends ArrayAdapter {
    // declare the Context
    private Context context;

    // declare the LayoutInflater
    private LayoutInflater inflater;

    // declare the variable to save the gif urls
    private String[] gifUrls;

    // create a GIFListAdapter object with the given parameters
    public GIFListAdapter(Context context, String[] gifUrls) {
        super(context, R.layout.gif_list_item, gifUrls);
        this.context = context;
        this.gifUrls = gifUrls;

        inflater = LayoutInflater.from(context);
    }

    // internal class
    static class ViewGIF {
        public ImageView gifView;
        public TextView gifUrl;
    }

    /**
     * display the gifs in the given view
     */
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // a ViewGIF to show the gif and link
        ViewGIF viewGIF;
        if (convertView == null) {
            // if the convertView has not been set yet, it will get them by ids.
            convertView = inflater.inflate(R.layout.gif_list_item, null);
            viewGIF = new ViewGIF();
            // find the ImageView for gif by id
            viewGIF.gifView = convertView.findViewById(R.id.gif);
            // find the TextView for the gif link
            viewGIF.gifUrl = convertView.findViewById(R.id.gifUrl);

            convertView.setTag(viewGIF);
        } else {
            viewGIF = (ViewGIF) convertView.getTag();
        }

        // get the gif url with the give position
        String gifUrl = this.gifUrls[position];

        // set the gif link
        viewGIF.gifUrl.setText("Link: " + gifUrl);


        /// display the give in the view
        Glide.with(context)
                .load(this.gifUrls[position])
                .into((ImageView) convertView.findViewById(R.id.gif));
        return convertView;
    }
}
