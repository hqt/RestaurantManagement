package com.view.menu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.helper.SendingPostRequest;
import com.model.Dish;
import com.view.MainActivity;
import com.view.R;

public class DescriptionScreenFragment extends Fragment {

	MainActivity activity;
	DishSelectionFragment father;
	
	/** currently time */
	String time;
	
	ListView listView1;
	
	/** Adapter for this View */
	DescriptionAdapter adapter;
	
	/** List contain all dishes. 
	 *  Convert from Map
	 */
	List<Dish> orderDishes = new ArrayList<Dish>();
	
	/** EditText for choose table number */
	EditText editText;
	
	/** must-be empty constructor */
	public DescriptionScreenFragment() {
		/** convert time to string */
		Calendar c = Calendar.getInstance(); 
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR);
		int minute = c.get(Calendar.MINUTE);
		time = year + "_" + month + "_" + date + "_" + hour + "_" + minute;
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = (MainActivity) activity;
		
		/** convert map to list */
		for (Entry<String, Dish> entry : this.activity.selectedDishes.entrySet()) {
			orderDishes.add(entry.getValue());
		}
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.description_screen_fragment, container, false);
		
		adapter = new DescriptionAdapter(activity, this, R.layout.listview_item_row, orderDishes);
    	
    	listView1 = (ListView) rootView.findViewById(R.id.listView1);

		View header = (View) getActivity().getLayoutInflater().inflate(R.layout.listview_header_row, null);
		TextView txtView = (TextView) (header).findViewById(R.id.txtHeader);
    	txtView.setText("Customer Order");
		
		listView1.addHeaderView(header);
		listView1.setAdapter(adapter);
		
		
		Button orderButton = (Button) (rootView).findViewById(R.id.btnOrder);
		
		orderButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				uploadOrder();
			}
		});
		
		Button backButton = (Button) (rootView).findViewById(R.id.btnCancel);
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DishSelectionFragment fragmentSpec = new DishSelectionFragment();
				getActivity().getSupportFragmentManager().beginTransaction().
				replace(R.id.item_detail_container, fragmentSpec).commit();

			}
		});
		
		/** set table number for current order */
		editText = (EditText) (rootView).findViewById(R.id.tableNo);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
		Boolean isCustomer = preferences.getBoolean("customermode", true);
		int table = preferences.getInt("tableno", 1);
		if (isCustomer) {
			editText.setText(table + "");
			/// make editText cannot edit
			editText.setEnabled(false);
		}
		else {
			// not customer
			editText.setText(table + "");
		}
		
		return rootView;
	}
	
	public void uploadOrder() {
		
		// convert java object to JSON format,
		// and returned as JSON formatted string
		Gson gson = new Gson();
		String jsonFood = gson.toJson(activity.selectedDishes);
		
		// upload data to server
		String url = MainActivity.server + "/" + "orders";
		
		// take data from table
		int table = 1;
		try {
			table = Integer.parseInt(editText.getText().toString());
		}
		catch (Exception e) {
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
			table = preferences.getInt("tableno", 1);
			
		}
		Map<String, String> values = new HashMap<String, String>();
		values.put("order[date_create]", time);
		values.put("order[table]", table + "");
		values.put("order[dishes]", jsonFood);
		values.put("order[price]", activity.price + "");
		values.put("order[status]", "waiting");
		values.put("order[currency]", "VND");    
		
		
		/*AsyncTask task = new SendingPostRequest(activity, url, values);
		Object result = task.execute().get();*/
		new SendingPostRequest(activity, url, values).execute();
		
	}
}
