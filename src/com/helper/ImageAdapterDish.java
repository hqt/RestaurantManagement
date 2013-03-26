package com.helper;

import java.io.InputStream;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.model.Dish;
import com.view.MainActivity;
import com.view.R;
import com.view.menu.DishSelectionFragment;

public class ImageAdapterDish extends BaseAdapter {
	private Context context;
	DishSelectionFragment father;
	MainActivity activity;
	private final List<Dish> dishValues;
 
	public ImageAdapterDish(DishSelectionFragment father, Context context, List<Dish> dishValues) {
		this.context = context;
		this.father = father;
		this.activity = (MainActivity) activity;
		this.dishValues = dishValues;
	}
 
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
 
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View gridView;
 
		if (convertView == null) {
 
			gridView = new View(context);
 
			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.mobiledish, null);
 
			// set value into name textview
			TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label_dish);
			textView.setText(dishValues.get(position).getName());
			
			// set value into price textview
			textView = (TextView) gridView.findViewById(R.id.grid_item_price_dish);
			textView.setText(dishValues.get(position).getPrice() + "");
			
			// set image based on selected text
			ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image_dish);
			
			// assign image base on 
			String category_name = dishValues.get(position).getImage();
			AssetManager assetManager = context.getAssets();
	        InputStream istr;   
			try {
				 istr = assetManager.open(category_name);
				 Drawable d = Drawable.createFromStream(istr, null);
				 imageView.setImageDrawable(d);
			     istr.close();
			} catch (Exception e) {
				Log.i("error", "Error when select icon");
			}
			
			final CheckBox chkDish = (CheckBox) gridView.findViewById(R.id.chkDish);
			chkDish.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View v) {
				  double discount = dishValues.get(position).getDiscount();

				  if (chkDish.isChecked()) {
					  activity.price += dishValues.get(position).getPrice() * (100 - discount) * 100;
				  } else {
					  activity.price -= dishValues.get(position).getPrice() * (100 - discount) * 100;
				  }
			  }
			});
 
		} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
 
	@Override
	public int getCount() {
		return dishValues.size();
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
