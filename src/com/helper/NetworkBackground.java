package com.helper;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.view.MainActivity;

public abstract class NetworkBackground extends AsyncTask<String, Void, Boolean> {

	/** application context */
	public MainActivity activity;
	
	/** progress dialog to show user that the backup is processing. */
    public ProgressDialog dialog;
    
    /** status of excute */
    public boolean status = true;
	
	public NetworkBackground(MainActivity activity) {
		this.activity = activity;
		dialog = new ProgressDialog(activity);
	}
	
	@Override
	 protected void onPreExecute() {
		 this.dialog.setMessage("Progress is loading");
		 this.dialog.show();
	 }
	 
	 @Override
	protected void onPostExecute(final Boolean success) {
		 
		 if (status == false) {
			 dialog.setMessage("Error when retrieve data from server");
		 }
		 else if (dialog.isShowing()) {
			 dialog.dismiss();
		 }
	 }
	
	 /** must implement this method */
	@Override
	protected abstract Boolean doInBackground(String... params);

}
