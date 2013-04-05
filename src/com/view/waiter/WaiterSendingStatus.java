package com.view.waiter;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.helper.NetworkBackground;
import com.view.MainActivity;

public class WaiterSendingStatus extends NetworkBackground {
	
	WaiterViewFragment father;
	int position;
	
	public WaiterSendingStatus(MainActivity activity, WaiterViewFragment father, int position) {
		super(activity);
		this.father = father;
		this.position = position;
	}

	@Override
	protected void onPostExecute(Boolean success) {
		father.getTable(position).setStatus("dirty");
		
		/// notice change
		father.adapter.notifyDataSetChanged();
		
		super.onPostExecute(success);
	}

	@Override
	protected Boolean doInBackground(String... urls) {
		String url = urls[0];
		HttpClient httpClient = new DefaultHttpClient();  
		HttpGet httpGet = new HttpGet(url);
		try {
			httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
