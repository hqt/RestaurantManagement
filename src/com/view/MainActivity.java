package com.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import com.model.Dish;
import com.model.MenuLeftList;
import com.model.Model;
import com.view.busboy.BusboyViewFragment;
import com.view.cook.CookFragment;
import com.view.host.HostViewFragment;
import com.view.manager.LoginManager;
import com.view.menu.CategoryFragment;
import com.view.order.OrderFragment;
import com.view.waiter.WaiterViewFragment;

/**
 * An activity representing a list of Items. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link ItemDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragment} and the item details (if present) is a
 * {@link ItemDetailFragment}.
 * <p>
 * This activity also implements the required {@link ItemListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class MainActivity extends FragmentActivity implements ItemListFragment.Callbacks {
	
	private boolean mTwoPane;
	public Model model;
	
	/** filter of dishes for screen view */
	public List<Dish> currentDishes = new ArrayList<Dish>();
	
	/** all dishes that user has selected */
	public Map<String, Dish> selectedDishes = new HashMap<String, Dish>();
	
	/** total price that user has selected */
	public double price = 0;
	
	/** link for all system */
	public static String server = "http://10.0.2.2:3000";
	
	/** default table for system */
	public static int tableno = 1;
	
	/** load all data need from server */
	public MainActivity() {
	}

	boolean DEVELOPER_MODE = true;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		if (DEVELOPER_MODE) {
	         StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
	                 .detectDiskReads()
	                 .detectDiskWrites()
	                 .detectNetwork()   // or .detectAll() for all detectable problems
	                 .penaltyLog()
	                 .build());
	         StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
	                 .detectLeakedSqlLiteObjects()
	                 .detectLeakedClosableObjects()
	                 .penaltyLog()
	                 .penaltyDeath()
	                 .build());
	     }
		
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.RGBA_8888);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

		 // read data
		model = new Model(this);
		
		/** 
		 * make this work on asyntask
		 * model.parsingJSONFood()
		 * model.updateTable()
		 * move this code into aysnctask
		 */          
		AsyncTask downloadtask = new ModelAsynTask(this).execute();
		
		// download all image
		
		// test upload
		/*String url = "http://10.0.2.2:3000/tests";
		Map<String, String> values = new HashMap<String, String>();
		values.put("test[username]", "thao");
		values.put("test[id]", "1993");
		AsyncTask uploadtask = new SendingPostRequest(this, url, values).execute();
		*/
		
		
		/** .
		 * after all transaction
		 * assign to dishes of MainActivity
		 * this design pattern should apply for all later !!! 
		 */
		// dishes = model.getDishes();
		setContentView(R.layout.activity_item_list);

		if (findViewById(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((ItemListFragment) getSupportFragmentManager().findFragmentById(
					R.id.item_list)).setActivateOnItemClick(true);
			
			/** set default screen for right fragment */
			DefaultRightFragment fragment = new DefaultRightFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragment).commit();
		}
		
	}

	/**
	 * Callback method from {@link ItemListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */    
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id); // ("item_id", id)
			
			MenuLeftList.LeftMenuCategories choice = MenuLeftList.LeftMenuCategories.valueOf(id.toUpperCase());
			switch (choice) {
				case MENU:
					CategoryFragment fragment1 = new CategoryFragment();
					fragment1.setArguments(arguments);
					getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragment1).commit();
					break;
				case ORDER:
					OrderFragment fragment2 = new OrderFragment();
					fragment2.setArguments(arguments);
					getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragment2).commit();
					break;
				case HOST:
					HostViewFragment hostViewFragment = new HostViewFragment();
					hostViewFragment.setArguments(arguments);
					getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, hostViewFragment).commit();
					break;
				case WAITER:
					WaiterViewFragment waiterViewFragment = new WaiterViewFragment();
					waiterViewFragment.setArguments(arguments);
					getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, waiterViewFragment).commit();
					break;   
				case BUSBOY:
					BusboyViewFragment busboyViewFragment = new BusboyViewFragment();
					busboyViewFragment.setArguments(arguments);
					getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, busboyViewFragment).commit();
					break;   
				case COOK:
					CookFragment cookFragment = new CookFragment();
					cookFragment.setArguments(arguments);
					getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, cookFragment).commit();
					break;
				case MANAGER:
					LoginManager loginManager = new LoginManager();
					loginManager.setArguments(arguments);
					getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, loginManager).commit();
					break;
				case SETTING:
					SettingFragment settingFragment = new SettingFragment();
					settingFragment.setArguments(arguments);
					getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, settingFragment).commit();
					break;
				case ABOUT:
					AboutFragment aboutFragment = new AboutFragment();
					aboutFragment.setArguments(arguments);
					getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, aboutFragment).commit();
					break;
			default:
				break;
			}
		} else {
			/* 
			 * In single-pane mode, simply start the detail activity for the selected item ID.
			 * Using intent to call another activity
			 */
			MenuLeftList.LeftMenuCategories choice = MenuLeftList.LeftMenuCategories.valueOf(id.toUpperCase());
			switch (choice) {
				case MENU:
					break;
				case ORDER:
					break;
					// from this will not implement
				case WAITER:
				case COOK:
				case MANAGER:
				case ABOUT:
					Intent detailIntent = new Intent(this, ItemDetailActivity.class);
					detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
					startActivity(detailIntent);
					break;
			default:
				break;
			}
		}
	}
	
	public List<Dish> getDish() { return model.getDishes(); }
	public void setCurrentDish(List<Dish> currentDishes) { 
		this.currentDishes = currentDishes;
	}
}
