package com.view.menu;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.model.Dish;
import com.view.MainActivity;
import com.view.R;

public class DishSelectionFragment extends Fragment {    

	
	GridView gridView;
	List<Dish> dishes;
	
	MainActivity activity;
	TextView txtPrice;
	String[] CATEGORIES;
	
	public DishSelectionFragment() {
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		this.activity = (MainActivity) activity;
		dishes = this.activity.currentDishes;
		CATEGORIES = new String[dishes.size()];
		for (int i = 0; i < dishes.size(); i++) {
			CATEGORIES[i] = dishes.get(i).getName();
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.dish_selection_fragment, container, false);
		
		gridView = (GridView) (rootView).findViewById(R.id.gridView2);
		gridView.setAdapter(new ImageAdapterDish(this, getActivity(), activity.currentDishes));
		
		txtPrice = (TextView) (rootView).findViewById(R.id.text_price_dish);
		txtPrice.setText("Price : " + activity.price);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				
				// make notice
				Toast.makeText(getActivity().getApplicationContext(),
				   ((TextView) v.findViewById(R.id.grid_item_label_dish)).getText(), 
				   Toast.LENGTH_SHORT).show();
				
			}
		});
		
		Button btnOrder = (Button) (rootView).findViewById(R.id.btnOrder);
		btnOrder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DescriptionScreenFragment fragmentSpec = new DescriptionScreenFragment();
				activity.getSupportFragmentManager().beginTransaction().
				replace(R.id.item_detail_container, fragmentSpec).commit();
				
			}
		});
		
		Button btnBack = (Button) (rootView).findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CategoryFragment fragment = new CategoryFragment();
				activity.getSupportFragmentManager().beginTransaction().
				replace(R.id.item_detail_container, fragment).commit();
			}
		});
		
		return rootView;
	}
	
	public void setPriceText(double price) {
		txtPrice.setText("Total Price: " + price);
	}

}
