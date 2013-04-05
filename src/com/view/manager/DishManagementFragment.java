package com.view.manager;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.model.Dish;
import com.view.ItemDetailFragment;
import com.view.MainActivity;
import com.view.R;
import com.view.menu.CategoryFragment;

public class DishManagementFragment extends Fragment {

	/** MainActivity for reference */
	public MainActivity activity;
	
	/** listview contains all items */
	private ListView listView1;
	
	/** adapter for this view */
	DishAdapter adapter;
	
	/** data for this fragment
	 * copy from main activity
	 */
	List<Dish> dishes;
	
	/** empty constructor */
	public DishManagementFragment() {
	}
 

	    
	    @Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);

			this.activity = (MainActivity) activity;
			dishes = this.activity.model.food;
		}
	    
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	      super.onActivityCreated(savedInstanceState);
	    }

	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	    }
	    
	    @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	    	View rootView = inflater.inflate(R.layout.dish_management_fragment, container, false);
	    	
			
	    	adapter = new DishAdapter(activity, this, R.layout.listview_item_row_fix_size, dishes);
	    	
	    	listView1 = (ListView) rootView.findViewById(R.id.listView1);

			View header = (View) getActivity().getLayoutInflater().inflate(R.layout.listview_header_row, null);
			TextView txtView = (TextView) (header).findViewById(R.id.txtHeader);
	    	txtView.setText("All Restaurant dishes");

			
			listView1.addHeaderView(header);
			listView1.setAdapter(adapter);
			
			listView1.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
					DishDetailFragment dishDetailFragment = new DishDetailFragment();
					Bundle arguments = new Bundle();
					arguments.putInt("id", position);
					dishDetailFragment.setArguments(arguments);
					activity.getSupportFragmentManager().beginTransaction().
					replace(R.id.item_detail_container, dishDetailFragment).commit();
				}
			});
			
			return rootView;
		}
	    
}
