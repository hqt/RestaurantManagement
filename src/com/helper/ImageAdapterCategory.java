package com.helper;

import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.view.R;

public class ImageAdapterCategory extends BaseAdapter {
	private Context context;
	private final String[] categoryValues;
 
	public ImageAdapterCategory(Context context, String[] categoryValues) {
		this.context = context;
		this.categoryValues = categoryValues;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
 
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View gridView;
 
		if (convertView == null) {
 
			gridView = new View(context);
 
			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.mobile, null);
 
			// set value into textview
			TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
			textView.setText(categoryValues[position]);
 
			// set image based on selected text
			ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
			
			
			/*int asset_id = context.getResources().getIdentifier("food", "drawable", context.getPackageName());
			imageView.setImageResource(asset_id);*/
			
			AssetManager assetManager = context.getAssets();

	        InputStream istr;   
			try {
				 istr = assetManager.open(categoryValues[position] + ".jpg");
				 Drawable d = Drawable.createFromStream(istr, null);
				 imageView.setImageDrawable(d);
			     istr.close();
			} catch (Exception e) {
				Log.i("error", "Error when select icon");
			}
	       
			
			
			
			/*if (asset_id > 0) {
				imageView.setImageResource(asset_id);
			}
			else {
				// cannot read, make default
				asset_id = context.getResources().getIdentifier("ic", "drawable", context.getPackageName());
				imageView.setImageResource(asset_id);
			}*/
 
		} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
 
	@Override
	public int getCount() {
		return categoryValues.length;
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
 
}
