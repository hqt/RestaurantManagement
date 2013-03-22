/*******************************************************************************
 * Copyright 2012 Gianrico D'Angelis  -- gianrico.dangelis@gmail.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package biz.easymenu.easymenung;


import java.io.FileNotFoundException;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class EasymenuNGActivity extends FragmentActivity {
    /** Called when the activity is first created. */
	
	public static final String TAG = "EasymenuNG";
	
	MenuPagerAdapter adapter = null;
	ViewPager pager = null;
	
	ProgressBar pBar = null;
	
	EmPrefs emp = null;
	Emrpc rpc = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);
        
        emp = new EmPrefs(this);
        rpc = new Emrpc(this);
        
        try {
			((ImageView)findViewById(R.id.logoImg)).setImageBitmap(BitmapFactory.decodeStream(
					openFileInput("logo.img")));
		} catch (FileNotFoundException e) {
			Log.e(EasymenuNGActivity.TAG,"Error image file not found: "+e.getMessage());
		}
        
        this.findViewById(R.id.lblTable).setClickable(true);
        this.findViewById(R.id.lblTable).setOnLongClickListener(new OnLongClickListener(){

			@Override
			public boolean onLongClick(View v) {
				
				((TextView)findViewById(R.id.lblTable)).setText("");
        		findViewById(R.id.btnMenu).setEnabled(false);
        		findViewById(R.id.btnDrinks).setEnabled(false);
        		findViewById(R.id.btnWaiter).setEnabled(false);
        		findViewById(R.id.btnOrder).setEnabled(false);
        		findViewById(R.id.btnBill).setEnabled(false);
        		findViewById(R.id.btnConfig).setVisibility(View.GONE);
				
		        FragmentManager fm = getSupportFragmentManager();
		    	FragmentTransaction ft ;
		    	Fragment f = null;
		    	f = new TableListFragment();
		    	if(fm.findFragmentByTag("rightfragment")!=null){
	    			ft = fm.beginTransaction();
	    			ft.setCustomAnimations(android.R.anim.fade_in,
	    	                android.R.anim.fade_out);
	    			ft.remove(fm.findFragmentByTag("rightfragment"));
	    			ft.commit();
	    		}
		    	ft = fm.beginTransaction();
				ft.setCustomAnimations(android.R.anim.fade_in,
		                android.R.anim.fade_out);
				ft.add(R.id.rightcontent,f,"rightfragment");
				ft.commit();
				return true;
			}
        	
        });
        
        FragmentManager fm = getSupportFragmentManager();
    	FragmentTransaction ft ;
    	Fragment f = null;
    	f = new TableListFragment();
    	ft = fm.beginTransaction();
		ft.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
		ft.add(R.id.rightcontent,f,"rightfragment");
		ft.commit();
        
    }
    
    public void leftMenuClicked (View v){
    	FragmentManager fm = getSupportFragmentManager();
    	FragmentTransaction ft ;
    	Fragment f = null;
    	
    	switch(v.getId()){
	    	case R.id.btnMenu:
	    		f =  new FoodMenuFragment(false);
	    		if(fm.findFragmentByTag("rightfragment")!=null){
	    			ft = fm.beginTransaction();
	    			ft.setCustomAnimations(android.R.anim.fade_in,
	    	                android.R.anim.fade_out);
	    			ft.remove(fm.findFragmentByTag("rightfragment"));
	    			ft.commit();
	    		}
	    		ft = fm.beginTransaction();
	    		ft.setCustomAnimations(android.R.anim.fade_in,
	                    android.R.anim.fade_out);
    			ft.add(R.id.rightcontent,f,"rightfragment");
    			ft.commit();
	    		
	    		break;
	    	case R.id.btnDrinks:
	    		f =  new FoodMenuFragment(true);
	    		if(fm.findFragmentByTag("rightfragment")!=null){
	    			ft = fm.beginTransaction();
	    			ft.setCustomAnimations(android.R.anim.fade_in,
		                    android.R.anim.fade_out);
	    			ft.remove(fm.findFragmentByTag("rightfragment"));
	    			ft.commit();
	    		}
	    		ft = fm.beginTransaction();
	    		ft.setCustomAnimations(android.R.anim.fade_in,
	                    android.R.anim.fade_out);
    			ft.add(R.id.rightcontent,f,"rightfragment");
    			ft.commit();
	    		break;
	    	case R.id.btnOrder:
	    		f =  new ReviewPlaceFragment();
	    		if(fm.findFragmentByTag("rightfragment")!=null){
	    			ft = fm.beginTransaction();
	    			ft.setCustomAnimations(android.R.anim.fade_in,
		                    android.R.anim.fade_out);
	    			ft.remove(fm.findFragmentByTag("rightfragment"));
	    			ft.commit();
	    		}
	    		ft = fm.beginTransaction();
	    		ft.setCustomAnimations(android.R.anim.fade_in,
	                    android.R.anim.fade_out);
    			ft.add(R.id.rightcontent,f,"rightfragment");
    			ft.commit();
	    		break;	
	    	case R.id.btnBill:
	    		f =  new PayBillFragment();
	    		if(fm.findFragmentByTag("rightfragment")!=null){
	    			ft = fm.beginTransaction();
	    			ft.setCustomAnimations(android.R.anim.fade_in,
		                    android.R.anim.fade_out);
	    			ft.remove(fm.findFragmentByTag("rightfragment"));
	    			ft.commit();
	    		}
	    		ft = fm.beginTransaction();
	    		ft.setCustomAnimations(android.R.anim.fade_in,
	                    android.R.anim.fade_out);
    			ft.add(R.id.rightcontent,f,"rightfragment");
    			ft.commit();
	    		break;	
	    	case R.id.btnWaiter:
	    		if(rpc.callWaiter(emp.getSid())){
	    			ft = fm.beginTransaction();
	    			ErrorFragment ferr = new ErrorFragment(getResources().getString(R.string.waiterNotified)); 
	    			Fragment prev = fm.findFragmentByTag("errorDialog");
	    			if (prev != null) {
	    				ft.remove(prev);
	    				ft.commit();
	    			}
	    			ferr.show(ft, "errorDialog");
	    		}
	    		break;
	    	case R.id.btnConfig:
	    		f =  new SettingsFragment();
	    		if(fm.findFragmentByTag("rightfragment")!=null){
	    			ft = fm.beginTransaction();
	    			ft.setCustomAnimations(android.R.anim.fade_in,
		                    android.R.anim.fade_out);
	    			ft.remove(fm.findFragmentByTag("rightfragment"));
	    			ft.commit();
	    		}
	    		ft = fm.beginTransaction();
	    		ft.setCustomAnimations(android.R.anim.fade_in,
	                    android.R.anim.fade_out);
    			ft.add(R.id.rightcontent,f,"rightfragment");
    			ft.commit();
    			break;
	    	default:
	    		break;
    	}
	 }
    
}
