package com.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.model.Dish;
import com.view.DefaultRightFragment;
import com.view.MainActivity;
import com.view.R;

public class SendingPostRequest extends  AsyncTask<String, Void, String> {
	
	String url;
	Map<String, String> values;
	
	/** application context */
	MainActivity activity;
	
	/** progress dialog to show user that the backup is processing. */
    private ProgressDialog dialog;
    
	
	public SendingPostRequest(MainActivity activity, String url, Map<String, String> values) {
		this.url = url;
		this.values = values;
		this.activity = activity;
		dialog = new ProgressDialog(activity);
	}
	
	@Override
	 protected void onPreExecute() {
		super.onPreExecute();
		this.dialog.setMessage("Progress is sending");
		this.dialog.show();
	 }
	 
	 @Override
	protected void onPostExecute(String result) {
		 
		// reset all data
		activity.selectedDishes = new HashMap<String, Dish>();
		activity.price = 0;
		activity.currentDishes = new ArrayList<Dish>();
					
		DefaultRightFragment fragment = new DefaultRightFragment();
		activity.getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragment).commit();
			
		super.onPostExecute(result);
		
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
		

		/*if(result.equals("working")){
			Toast.makeText(activity.getApplicationContext(), "HTTP POST is working...", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(activity.getApplicationContext(), "Invalid POST req...", Toast.LENGTH_LONG).show();
		}*/
	}			

	@Override
	protected String doInBackground(String... params) {

		HttpClient httpClient = new DefaultHttpClient();

		/**
		 * In a POST request, we don't pass the values in the URL.
		 * Therefore we use only the web page URL as the parameter of the HtpPost arguemnt
		 */
		HttpPost httpPost = new HttpPost(url);

		/**
		 * Because we are not passing values over the URL, we should have a mechanism to pass the values that can be
		 * uniquely separate by the other end.
		 * To achieve that we use BasicNameValuePair				
		 * Things we need to pass with the POST request
		 */
		List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : values.entrySet()) {
			BasicNameValuePair basicNameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
			
			/**
			 * We add the content that we want to pass with the POST request to as name-value pairs
			 * Now we put those sending details to an ArrayList with type safe of NameValuePair
			 */
			nameValuePairList.add(basicNameValuePair);
		}
		
		/** after prepare for data. prepare for sending */
		try {
			
			/**
			 *  UrlEncodedFormEntity is an entity composed of a list of url-encoded pairs. 
			 *  This is typically useful while sending an HTTP POST request. 
			 */
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);

			/** setEntity() hands the entity (here it is urlEncodedFormEntity) to the request. */
			httpPost.setEntity(urlEncodedFormEntity);

			try {
				/**
				 * HttpResponse is an interface just like HttpPost
				 * therefore we can't initialize them
				 */
				HttpResponse httpResponse = httpClient.execute(httpPost);
				
				/**
				 * according to the JAVA API, InputStrem constructor do nothing.
				 * So we can't initialize InputStrem although it is not an interface
				 */
				InputStream inputStream = httpResponse.getEntity().getContent();

				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				StringBuilder stringBuilder = new StringBuilder();

				String bufferedStrChunk = null;

				while((bufferedStrChunk = bufferedReader.readLine()) != null){
					stringBuilder.append(bufferedStrChunk);
				}    

				Log.i("Debug", stringBuilder.toString());
				return stringBuilder.toString();

			} catch (ClientProtocolException cpe) {
				System.out.println("First Exception caz of HttpResponese :" + cpe);
				cpe.printStackTrace();
			} catch (IOException ioe) {
				System.out.println("Second Exception caz of HttpResponse :" + ioe);
				ioe.printStackTrace();
			}

		} catch (UnsupportedEncodingException uee) {
			System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
			uee.printStackTrace();
		}
		
		

		return null;
	}
	

}
