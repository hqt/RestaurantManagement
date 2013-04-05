package com.view.manager;

import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.model.Dish;
import com.view.R;

public class DishAdapter extends ArrayAdapter<Dish>{

    Context context; 
    int layoutResourceId;    
    DishManagementFragment father;
    
    public DishAdapter(Context context, DishManagementFragment father, int layoutResourceId, List<Dish> data) {
    	super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.father = father;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        OrderHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new OrderHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            
            row.setTag(holder);
        }
        else
        {
            holder = (OrderHolder) row.getTag();
        }
        
        Dish dish = father.dishes.get(position);
        
        String text = dish.getName() + "\n" +
        		      dish.getPrice() + " " + dish.getCurrency() + "\n" +
        		      dish.getTag();
        holder.txtTitle.setText(text);

        AssetManager assetManager = context.getAssets();
        InputStream istr;   
		try {
			 istr = assetManager.open(dish.getImage());
			 Drawable d = Drawable.createFromStream(istr, null);
			 holder.imgIcon.setImageDrawable(d);
		     istr.close();
		} catch (Exception e) {
			Log.i("error", "Error when select icon. Change to default");
			holder.imgIcon.setImageResource(R.drawable.food);
		}

        return row;
    }
    
    
    /** ALWAYS SHOULD USE TO IMPLEMENT THIS */
    @Override
	public int getCount() {
		return father.dishes.size();
	}

	static class OrderHolder
    {
        ImageView imgIcon;    
        TextView txtTitle;
    }
}