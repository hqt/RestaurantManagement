package com.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.WindowManager;

import com.helper.NetworkBackground;
import com.model.Dish;
import com.model.Model;
import com.view.menu.CategoryFragment;

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
	
	/** using enum for switch case */
	public static enum LeftMenuCategories {
	    MENU,
	    ORDER,
	    WAITER,
	    COOK,
	    MANAGER,
	    SETTING,
	    ABOUT,
	    ACCEPT
	  }

	private boolean mTwoPane;
	public Model model;
	/** all dishes in restaurant system */
	public List<Dish> dishes;
	
	/** filter of dishes for screen view */
	public List<Dish> currentDishes = new ArrayList<Dish>();
	
	/** all dishes that user has selected */
	public Map<String, Dish> selectedDishes = new HashMap<String, Dish>();
	
	/** total price that user has selected */
	public double price = 0;
	
	/** load all data need from server */
	public MainActivity() {
		
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.RGBA_8888);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);

		
		// read data
		model = new Model(this);
		
		/** 
		 * make this work on asyntask
		 * model.parsingJSONFood()
		 * move this code into aysnctask
		 */
		AsyncTask task = new NetworkBackground(this).execute();
		
		dishes = model.getDishes();
		
		// download all image
		
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
			
			LeftMenuCategories choice = LeftMenuCategories.valueOf(id.toUpperCase());
			switch (choice) {
				case MENU:
					CategoryFragment fragment1 = new CategoryFragment();
					Log.i("tag", "step one");
					fragment1.setArguments(arguments);
					getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragment1).commit();
					break;
				case ORDER:
					LoginFragment fragment2 = new LoginFragment();
					Log.i("tag", "step one");
					fragment2.setArguments(arguments);
					getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragment2).commit();
					break;
					// from this will not implement
				case WAITER:
				case COOK:
				case MANAGER:
				case ABOUT:
					ItemDetailFragment fragmentSpec = new ItemDetailFragment();
					fragmentSpec.setArguments(arguments);
					getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragmentSpec).commit();
					break;
			default:
				break;
			}
		} else {
			/* 
			 * In single-pane mode, simply start the detail activity for the selected item ID.
			 * Using intent to call another activity
			 */
			LeftMenuCategories choice = LeftMenuCategories.valueOf(id.toUpperCase());
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
	
	public List<Dish> getDish() { return dishes; }
	public void setCurrentDish(List<Dish> currentDishes) { 
		this.currentDishes = currentDishes;
	}
}
