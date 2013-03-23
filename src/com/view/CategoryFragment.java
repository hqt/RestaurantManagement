package com.view;

import com.helper.ImageAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

public class CategoryFragment extends Fragment {

	GridView gridView;
	 
	static final String[] MOBILE_OS = new String[] { 
		"Android", "iOS","Windows", "Blackberry" };
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.category_fragment, container, false);
		
		gridView = (GridView) (rootView).findViewById(R.id.gridView1);
		gridView.setAdapter(new ImageAdapter(getActivity(), MOBILE_OS));
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Toast.makeText(getActivity().getApplicationContext(),
				   ((TextView) v.findViewById(R.id.grid_item_label)).getText(), 
				   Toast.LENGTH_SHORT).show();
 
			}
		});
		
		return rootView;
 
	}
}