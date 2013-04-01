package com.helper;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.view.MainActivity;

public abstract class NetworkBackground extends AsyncTask<String, Void, Boolean> {

	/** application context */
	public MainActivity activity;
	
	/** progress dialog to show user that the backup is processing. */
    public ProgressDialog dialog;
	
	public NetworkBackground(MainActivity activity) {
		this.activity = activity;
		dialog = new ProgressDialog(activity);
	}
	
	@Override
	 protected void onPreExecute() {
		 this.dialog.setMessage("Progress start");
		 this.dialog.show();
	 }
	 
	 @Override
	protected void onPostExecute(final Boolean success) {
		 if (dialog.isShowing()) {
			 dialog.dismiss();
		 }
	 }
	
	 /** must implement this method */
	@Override
	protected abstract Boolean doInBackground(String... params);

}
