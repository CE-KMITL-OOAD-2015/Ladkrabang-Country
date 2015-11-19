package com.awakenguys.kmitl.ladkrabangcountry;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.awakenguys.kmitl.ladkrabangcountry.model.Review;

import java.util.List;

public class ReviewListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Review> reviewItems;
	private TextView author;
	//ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public ReviewListAdapter(Activity activity, List<Review> reviewsItems) {
		this.activity = activity;
		this.reviewItems = reviewsItems;
	}

	@Override
	public int getCount() {
		return reviewItems.size();
	}

	@Override
	public Object getItem(int location) {
		return reviewItems.get(location);
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


		Review r = reviewItems.get(position);

		thumbNail.setImageResource(R.drawable.camera);

		topic.setText(r.getTopic());

		content.setText(r.getContent());

		author.setText(r.getAuthor());

		rating.setText(""+r.getRating());

		return convertView;
	}


}