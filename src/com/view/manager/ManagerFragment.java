package com.view.manager;


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

import com.view.MainActivity;
import com.view.R;

public class ManagerFragment extends Fragment {
	
	/** MainActivity for reference */
	public MainActivity activity;
	
	/** listview contains all items */
	private ListView listView1;
	
	/** data */
	String[] data = new String[] {"Graph", "View All Menu", "Manage Staff", "View All Order"};
	
	/** adapter for this listview */
	ManagerAdapter adapter;
	
	/** empty constructor */
	public ManagerFragment() {
	}
	
	    @Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);

			this.activity = (MainActivity) activity;
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

	    	View rootView = inflater.inflate(R.layout.order_fragment, container, false);
	    	
	    	adapter = new ManagerAdapter(activity, this, R.layout.listview_item_row, data);
	    	
	    	listView1 = (ListView) rootView.findViewById(R.id.listView1);

			View header = (View) getActivity().getLayoutInflater().inflate(R.layout.listview_header_row, null);
			TextView txtView = (TextView) (header).findViewById(R.id.txtHeader);
	    	txtView.setText("Management Tools");

			
			listView1.addHeaderView(header);
			listView1.setAdapter(adapter);
			
			listView1.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
					switch (position) {
						case 2:
							GraphFragment graphFragment = new GraphFragment();
							activity.getSupportFragmentManager().beginTransaction().
							replace(R.id.item_detail_container, graphFragment).commit();
							break;
						case 3:
							DishManagementFragment dishManagementFragment = new DishManagementFragment();
							activity.getSupportFragmentManager().beginTransaction().
							replace(R.id.item_detail_container, dishManagementFragment).commit();
							break;
						default:
							break;
					}
				}
			}); 

			return rootView;
	    }

	    
		@Override
		public void onPause() {
			super.onPause();
		}
	    
	    
	    
}
