package com.view.cook;

import com.view.R;
import com.view.R.layout;
import com.view.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class OrderDetailFragment extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_detail_fragment);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_detail, menu);
		return true;
	}

}
