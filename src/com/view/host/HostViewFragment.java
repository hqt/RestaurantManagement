package com.view.host;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.model.Table;
import com.view.MainActivity;
import com.view.R;
import com.view.host.ImageAdapterHost.TABLE_STATUS;

public class HostViewFragment extends Fragment {
	
	GridView gridView;
	 MainActivity activity;
	 
	 /** adapter for this gridview*/
	 ImageAdapterHost adapter;
	 
	 /** timer for update data periodically */
	 Timer timer;
	 
	 
	/** empty constructor */
	public HostViewFragment() {
	}
	
	 @Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = (MainActivity) activity;
	 }
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		final View rootView = inflater.inflate(R.layout.waiter_view_fragment, container, false);
		
		gridView = (GridView) (rootView).findViewById(R.id.waiterGridView);
		
		adapter = new ImageAdapterHost(activity, this, R.layout.gridview2, activity.model.getTables());
		
		gridView.setAdapter(adapter);
		
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
				
				// make notice
				Toast.makeText(getActivity().getApplicationContext(),
				   "Table " + getTable(position).getNo() + " has changed", 
				   Toast.LENGTH_SHORT).show();
				
				// change the status of system.
				TABLE_STATUS choice = TABLE_STATUS.valueOf(getTable(position).getStatus().toUpperCase());
				switch (choice) {
					case FREE:
						
						/// update data to server
						Thread t = new Thread() {
							public void run() {
								int id = getTable(position).getId();
								String url = MainActivity.server + "/tables/changes/free/" + id; 
								HttpClient httpClient = new DefaultHttpClient();  
								HttpGet httpGet = new HttpGet(url);
								try {
									httpClient.execute(httpGet);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						};
						
						/** starting upload to server and waiting for it */
						t.start();
						try {
							t.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						getTable(position).setStatus("busy");
						
						/// notice change
						adapter.notifyDataSetChanged();
						break;
					case BUSY:
					case DIRTY:
					default:
						break;         
				}
			}
		});
		
		/** make service update after periodically time */
		timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				
				/// update table data from server
				/// because this update will change directly into model
				/// MainActivity and others reference directly to this
				///don't need do care about null point exception
				activity.model.parsingJSONTable();
				
				/** Using getActivity() instead for Handler as below */
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						adapter.notifyDataSetChanged();
						
					}
				});
				
			}
		};
		
		timer.schedule(timerTask, 3000, 6000);   
		
		return rootView;   
	}
	
	

	@Override
	public void onPause() {
		super.onPause();
		timer.cancel();
		timer.purge();
	}

	public Table getTable(int position) { return activity.model.getTables().get(position); }
	 
	
}
