package com.view.cook;

import com.helper.NetworkBackground;
import com.model.Order;
import com.view.MainActivity;

public class CookAsynTask extends NetworkBackground {

	CookFragment father;
	
	public CookAsynTask(MainActivity activity, CookFragment father) {
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
