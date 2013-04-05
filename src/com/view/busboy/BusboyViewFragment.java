package com.view.busboy;

import java.util.Timer;
import java.util.TimerTask;

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

public class BusboyViewFragment extends Fragment {
	
	GridView gridView;
	 MainActivity activity;
	 
	 /** adapter for this gridview*/
	 ImageAdapterTableBoy adapter;
	 
	 /** timer for update data periodically */
	 Timer timer;
	 TimerTask timerTask;
	 
	 
	/** empty constructor */
	public BusboyViewFragment() {
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
		
		adapter = new ImageAdapterTableBoy(activity, this, R.layout.gridview2, activity.model.getTables());
		
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
					
				case DIRTY:
						int idBusboy = getTable(position).getId();
						String url = MainActivity.server + "/tables/changes/dirty/" + idBusboy; 
						new BusboySendingStatus(activity, BusboyViewFragment.this, position).execute(url);
						break;
						
					case BUSY:
					case FREE:
					default:
						break;         
				}
			}
		});
		
		/** make service update after periodically time */
		timer = new Timer();
		timerTask = new TimerTask() {
			@Override
			public void run() {
				
				/// update table data from server
				/// because this update will change directly into model
				/// MainActivity and others reference directly to this
				///don't need do care about null point exception
				activity.model.parsingJSONTable();
				
				if (getActivity() == null) {
					timerTask.cancel();
					timer.cancel();
					return;
				}
				
				/** Using getActivity() instead for Handler as below */
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						adapter.notifyDataSetChanged();
						
					}
				});
				
			}
		};
		
		//timer.schedule(timerTask, 3000, 6000);   
		
		return rootView;   
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		timerTask.cancel();
		timer.cancel();
		timer.purge();
	}

	@Override
	public void onPause() {
		super.onPause();
		timerTask.cancel();
		timer.cancel();
		timer.purge();
	}

	public Table getTable(int position) { return activity.model.getTables().get(position); }
	 
	
}
