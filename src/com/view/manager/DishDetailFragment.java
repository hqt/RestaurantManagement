package com.view.manager;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.model.Dish;
import com.view.MainActivity;
import com.view.R;
import com.view.menu.CategoryFragment;

public class DishDetailFragment extends Fragment {

	/** MainActivity for reference */
	public MainActivity activity;
	
	EditText idTxt;
	EditText nameTxt;
	EditText priceTxt;
	EditText discountTxt;
	EditText currencyTxt;
	EditText tagTxt;
	EditText buyNumTxt;
	EditText imageTxt;
	EditText descriptionTxt;
	
	/** empty constructor */
	public DishDetailFragment() {
	}
 

	    
	    @Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);

			this.activity = (MainActivity) activity;
		}
	    
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	      super.onActivityCreated(savedInstanceState);
	    }

	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	    }
	    
	    @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	    	View rootView = inflater.inflate(R.layout.activity_dish_detail_fragment, container, false);
	    	
	    	idTxt = (EditText) (rootView).findViewById(R.id.dishid);
	    	nameTxt = (EditText) (rootView).findViewById(R.id.dishname);
	    	priceTxt = (EditText) (rootView).findViewById(R.id.dishprice);
	    	discountTxt = (EditText) (rootView).findViewById(R.id.dishdiscount);
	    	currencyTxt = (EditText) (rootView).findViewById(R.id.dishcurrency);
	    	tagTxt = (EditText) (rootView).findViewById(R.id.dishtag);
	    	buyNumTxt = (EditText) (rootView).findViewById(R.id.dishcount);
	    	imageTxt = (EditText) (rootView).findViewById(R.id.dishimagename);
	    	descriptionTxt = (EditText) (rootView).findViewById(R.id.dishdescription);
	    	
	    	int position = getArguments().getInt("id");
	    	Dish dish = activity.model.getDishes().get(position);
	    	idTxt.setText(dish.getId() + "");
	    	nameTxt.setText(dish.getName());
	    	priceTxt.setText(dish.getPrice() + "");
	    	discountTxt.setText(dish.getDiscount() + "");
	    	currencyTxt.setText(dish.getCurrency());
	    	tagTxt.setText(dish.getTag());
	    	buyNumTxt.setText("5");
	    	imageTxt.setText(dish.getImage());
	    	descriptionTxt.setText(dish.getDescription());
	    	
	    	Button btnBack = (Button)(rootView).findViewById(R.id.backBtn);
	    	btnBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					DishManagementFragment fragment1 = new DishManagementFragment();
					activity.getSupportFragmentManager().beginTransaction().
					replace(R.id.item_detail_container, fragment1).commit();
					
				}
			});
	    	
			return rootView;
		}
	    
}
