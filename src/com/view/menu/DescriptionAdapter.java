package com.view.menu;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.model.Dish;
import com.view.R;

public class DescriptionAdapter extends ArrayAdapter<Dish>{

    Context context; 
    int layoutResourceId;    
    DescriptionScreenFragment father;
    
    public DescriptionAdapter(Context context, DescriptionScreenFragment father, int layoutResourceId, List<Dish> data) {
        /** problem here !!!!
         * data change, but data of array adapter doesn't change !!!!
         * always implements getCount() to prevent problem !!!!
         */
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
        
        Dish dish = father.orderDishes.get(position);
        holder.imgIcon.setImageResource(R.drawable.food);
        holder.txtTitle.setText("Name: " + dish.getName() + "\n" +
        						"Discount: " + dish.getDiscount() + "%" + "\n" +
        						"Price: " + dish.getPrice() + " " + dish.getCurrency());
        
        return row;
    }
    
    
    /** ALWAYS SHOULD USE TO IMPLEMENT THIS */
    @Override
	public int getCount() {
		return father.orderDishes.size();
	}

	static class OrderHolder
    {
        ImageView imgIcon;    
        TextView txtTitle;
    }
}