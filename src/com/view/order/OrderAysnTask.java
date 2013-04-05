package com.view.order;

import com.helper.NetworkBackground;
import com.model.Order;
import com.view.MainActivity;

public class OrderAysnTask extends NetworkBackground {

	OrderFragment father;
	
	public OrderAysnTask(MainActivity activity, OrderFragment father) {
		super(activity);
		this.father = father;
	}
	
	@Override
	protected void onPostExecute(Boolean success) {

		/// notice change
		father.adapter.notifyDataSetChanged();
		
		super.onPostExecute(success);
	}

	@Override
	protected Boolean doInBackground(String... urls) {
		this.dialog.show();
		String url = urls[0];
		father.orders = Order.loadServerOrders(url);
		return true;
	}
	
	

}
