package com.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LoginFragment extends Fragment {
	
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the fragment
	 * (e.g. upon screen orientation changes).
	 */
	public LoginFragment() {
		Log.i("log", "login constructor");
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("log", "login on create");
        
    }
    
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_login, container, false);
		return rootView;
	}
}