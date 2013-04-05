package com.view.manager;

import com.view.MainActivity;
import com.view.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OrderManageFragment extends Fragment {
	
	MainActivity activity;
	
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
	    	
	    	/*adapter = new SimpleAdapter(activity, this, R.layout.listview_item_row, orders);
	    	
	    	listView1 = (ListView) rootView.findViewById(R.id.listView1);

			View header = (View) getActivity().getLayoutInflater().inflate(R.layout.listview_header_row, null);

			
			listView1.addHeaderView(header);
			listView1.setAdapter(adapter);
*/
	    	return rootView;
	    
	    }

}
