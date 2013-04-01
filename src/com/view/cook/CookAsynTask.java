package com.view.cook;

import com.helper.NetworkBackground;
import com.model.Order;
import com.view.MainActivity;

public class CookAsynTask extends NetworkBackground {

	CookFragment fragment;
	
	public CookAsynTask(MainActivity activity, CookFragment fragment) {
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
