package com.view.manager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.view.R;

public class LoginManager extends Fragment {
	
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the fragment
	 * (e.g. upon screen orientation changes).
	 */
	public LoginManager() {
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		final View rootView = inflater.inflate(R.layout.fragment_login, container, false);
		
		Button btn_login = (Button) rootView.findViewById(R.id.btnLogin);
		
		btn_login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username = ((EditText) rootView.findViewById(R.id.txtUser)).getText().toString();
				String password = ((EditText) rootView.findViewById(R.id.txtPassword)).getText().toString();
				
				if (username.equals("root") && password.equals("duyeu")) {
					Bundle arguments = new Bundle();
					ManagerFragment fragmentSpec = new ManagerFragment();
					getActivity().
					getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragmentSpec).commit();
				}
				else {
					((EditText) rootView.findViewById(R.id.txtUser)).setText("");
					((EditText) rootView.findViewById(R.id.txtUser)).setText("");
				}
			}
		});

		return rootView;
	}
}