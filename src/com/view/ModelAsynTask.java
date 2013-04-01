package com.view;

import com.helper.NetworkBackground;

public class ModelAsynTask extends NetworkBackground {

	public ModelAsynTask(MainActivity activity) {
		super(activity);
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		this.dialog.show();
		activity.model.parsingJSONFood();
		activity.model.parsingJSONTable();
		return true;
	}

}
