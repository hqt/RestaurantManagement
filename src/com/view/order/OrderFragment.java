package com.view.order;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.model.Order;
import com.view.MainActivity;
import com.view.R;

public class OrderFragment extends Fragment {
	
	
	public static enum ORDER_STATUS {
		WAITING,
		PREPARE,
		FINISH
	}
	
	/** MainActivity for reference */
	public MainActivity activity;
	
	/** listview contains all items */
	private ListView listView1;
	
	/** list all orders to display */
	public List<Order> orders;
	
	/** adapter for this listview */
	OrderAdapter adapter;
	
	/** Handler : Like timer, to schedule work after periodcally time */
	Handler handler;
	
	/** timer to make service run periodically */
	Timer timer;
	
	/** empty constructor */
	public OrderFragment() {
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
	    	
	    	/** load table data from server */
	    	final String url = MainActivity.server + "/orders/filter/waiting.json";
	    	
	    	AsyncTask task = new OrderAysnTask(activity, this).execute(url);
	    	
	    	/** wait for task finish */
	    	try {
				task.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
	    	
			
	    	adapter = new OrderAdapter(activity, this, R.layout.listview_item_row, orders);
	    	
	    	listView1 = (ListView) rootView.findViewById(R.id.listView1);

			View header = (View) getActivity().getLayoutInflater().inflate(R.layout.listview_header_row, null);

			
			listView1.addHeaderView(header);
			listView1.setAdapter(adapter);
			
			listView1.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
					
					/// update data to server
					Thread t = new Thread() {
						public void run() {
							int id = orders.get(position - 1).getId();
							String url = MainActivity.server + "/orders/changes/waiting/" + id; 
							HttpClient httpClient = new DefaultHttpClient();  
							HttpGet httpGet = new HttpGet(url);
							try {
								httpClient.execute(httpGet);
							} catch (ClientProtocolException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					};
					
					t.start();
					try {
						t.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
					/// remove from list
					orders.remove(position - 1);
					
					/// notice change
					adapter.notifyDataSetChanged();
				}
			});

			/** make service update after periodically time */
			//addUpdateService(url);
			timer = new Timer();
			TimerTask timerTask = new TimerTask() {
				@Override
				public void run() {
					
					orders = Order.loadServerOrders(url);
					
					/**
					 *  Using getActivity() instead for Handler as below
					 *  Because we update adapter.
					 *  should use on same thread creator (in this case, UI Thread)
					 */
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							adapter.notifyDataSetChanged();
							
						}
					});
					
					/*handler.post(new Runnable() {
						@Override
						public void run() {
							adapter.notifyDataSetChanged();
							
						}
					});  */
					
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
	    
	    
	    
}
