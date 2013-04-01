package com.view.order;

import com.helper.NetworkBackground;
import com.model.Order;
import com.view.MainActivity;

public class OrderAysnTask extends NetworkBackground {

	OrderFragment fragment;
	
	public OrderAysnTask(MainActivity activity, OrderFragment fragment) {
		super(activity);
		this.fragment = fragment;
	}

	@Override
	protected Boolean doInBackground(String... urls) {
		this.dialog.show();
		String url = urls[0];
		fragment.orders = Order.loadServerOrders(url);
		return true;
	}
	
	

}
