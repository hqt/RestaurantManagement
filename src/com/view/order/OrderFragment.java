package com.view.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

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
import android.widget.TextView;

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
	public List<Order> orders = new ArrayList<Order>();
	
	/** adapter for this listview */
	OrderAdapter adapter;
	
	/** Handler : Like timer, to schedule work after periodcally time */
	Handler handler;
	
	/** timer to make service run periodically */
	Timer timer;
	TimerTask timerTask;
	
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
	    	
	    	adapter = new OrderAdapter(activity, this, R.layout.listview_item_row, orders);

	    	/** loading data */
	    	AsyncTask task = new OrderAysnTask(activity, this).execute(url);
	    	
	    	listView1 = (ListView) rootView.findViewById(R.id.listView1);

			
	    	View header = (View) getActivity().getLayoutInflater().inflate(R.layout.listview_header_row, null);
	    	TextView txtView = (TextView) (header).findViewById(R.id.txtHeader);
	    	txtView.setText("Order List ^_^");
			
			listView1.addHeaderView(header);
			listView1.setAdapter(adapter);
			
			listView1.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
					
					int idOrder = orders.get(position - 1).getId();
					
					String url = MainActivity.server + "/orders/changes/waiting/" + idOrder; 
					
					new OrderSendingData(activity, OrderFragment.this, position).execute(url);
					
				}
			});

			/** make service update after periodically time */
			//addUpdateService(url);
			timer = new Timer();
			timerTask = new TimerTask() {
				@Override
				public void run() {
					
					final List<Order> newOrders = Order.loadServerOrders(url);
					
					/**
					 *  Using getActivity() instead for Handler as below
					 *  Because we update adapter.
					 *  should use on same thread creator (in this case, UI Thread)
					 */
					if (getActivity() == null) {
						timerTask.cancel();
						timer.cancel();
						return;
					}
					
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							orders = newOrders;
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
	    
	    
	    
}
