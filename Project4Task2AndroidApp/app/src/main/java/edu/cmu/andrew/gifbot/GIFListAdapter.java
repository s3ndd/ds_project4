package edu.cmu.andrew.gifbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class GIFListAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;

    private String[] gifUrls;

    public GIFListAdapter(Context context, String[] gifUrls) {
        super(context, R.layout.gif_list_item, gifUrls);
        this.context = context;
        this.gifUrls = gifUrls;

        inflater = LayoutInflater.from(context);
    }

    static class ViewGIF {
        public ImageView gifView;
        public TextView gifUrl;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewGIF viewGIF = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gif_list_item, null);
            viewGIF = new ViewGIF();
            viewGIF.gifView = convertView.findViewById(R.id.gif);
            viewGIF.gifUrl = convertView.findViewById(R.id.gifUrl);

            convertView.setTag(viewGIF);
        } else {
            viewGIF = (ViewGIF) convertView.getTag();
        }

        String gifUrl = this.gifUrls[position];

        viewGIF.gifUrl.setText("Link: " + gifUrl);


        Glide.with(context)
                .load(this.gifUrls[position])
                .into((ImageView) convertView.findViewById(R.id.gif));
        return convertView;
    }
}
