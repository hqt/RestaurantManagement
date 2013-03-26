package com.view.menu;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.helper.ImageAdapterCategory;
import com.model.Dish;
import com.view.MainActivity;
import com.view.R;

public class CategoryFragment extends Fragment {

	GridView gridView;
	 
	static final String[] CATEGORIES = new String[] {
		"food", "drink" };
	
	/** empty constructor */
	public CategoryFragment() {
		
	}
 
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.category_fragment, container, false);
		
		gridView = (GridView) (rootView).findViewById(R.id.gridView1);
		gridView.setAdapter(new ImageAdapterCategory(getActivity(), CATEGORIES));
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				
				// make notice
				Toast.makeText(getActivity().getApplicationContext(),
				   ((TextView) v.findViewById(R.id.grid_item_label)).getText(), 
				   Toast.LENGTH_SHORT).show();
				
				// filter list
				MainActivity activity = (MainActivity) getActivity();
				List<Dish> dishes = activity.getDish();
				List<Dish> filter = new ArrayList<Dish>();
				String text = CATEGORIES[position]; // ? Position
				for (int i = 0; i < dishes.size(); i++) {
					if (dishes.get(i).findTag(text)) {
						filter.add(dishes.get(i));
					}
				}
				activity.setCurrentDish(filter);
				
				// create a new activity
				Bundle arguments = new Bundle();
				DishSelectionFragment fragmentSpec = new DishSelectionFragment();
				fragmentSpec.setArguments(arguments);
				getActivity().
				getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragmentSpec).commit();
				
			}
		});
		
		return rootView;
 
	}
}