package com.awakenguys.kmitl.ladkrabangcountry;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.awakenguys.kmitl.ladkrabangcountry.model.Announce;


import java.util.List;

public class AnnounceListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Announce> announceItems;
    private TextView author;
    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public AnnounceListAdapter(Activity activity, List<Announce> announcesItems) {
        this.activity = activity;
        this.announceItems = announcesItems;
    }

    @Override
    public int getCount() {
        return announceItems.size();
    }

    @Override
    public Object getItem(int location) {
        return announceItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row,parent,false);

        //if (imageLoader == null)
        //	imageLoader = AppController.getInstance().getImageLoader();
        ImageView thumbNail = (ImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView topic = (TextView) convertView.findViewById(R.id.topic);
        TextView content = (TextView) convertView.findViewById(R.id.content);
        author = (TextView) convertView.findViewById(R.id.author);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);

        // getting movie data for the row
        Announce a = announceItems.get(position);

        // thumbnail image
        thumbNail.setImageResource(R.drawable.camera);

        // topic
        topic.setText(a.getTopic());

        // content
        content.setText(a.getContent());

        // genre
		/*String genreStr = "";
		for (String str : m.getGenre()) {
			genreStr += str + ", ";
		}
		genreStr = genreStr.length() > 0 ? genreStr.substring(0,
				genreStr.length() - 2) : genreStr;*/
        author.setText(a.getAuthor());


        return convertView;
    }


}