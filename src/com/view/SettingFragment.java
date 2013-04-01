package com.view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class SettingFragment extends Fragment {
	
	MainActivity activity;
	
	/** empty constructor */
	public SettingFragment() {
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = (MainActivity) activity;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.setting_fragment, container, false);
		
		Button okButton = (Button) (rootView).findViewById(R.id.okBtn);
		final EditText serverLink = (EditText) (rootView).findViewById(R.id.serverlink);
		final EditText tableNo = (EditText) (rootView).findViewById(R.id.tableno);
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
		serverLink.setText(preferences.getString("serverlink", "localhost"));
		tableNo.setText(preferences.getInt("tableno", 1) + "");
		
		
		okButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String serverText = serverLink.getText().toString();
				String tableText = tableNo.getText().toString();
				SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
				Editor edit = preferences.edit();
				edit.putString("serverlink", serverText);
				edit.putInt("tableno", Integer.parseInt(tableText));
				edit.commit(); 
				
			}
		});
		
		return rootView;
	}
	
	

}
