package com.view.order;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.model.Order;
import com.view.R;

public class OrderAdapter extends ArrayAdapter<Order>{

    Context context; 
    int layoutResourceId;    
    OrderFragment father;
    //List<Order> data;
    
    public OrderAdapter(Context context, OrderFragment father, int layoutResourceId, List<Order> data) {
        /** problem here !!!!
         * data change, but data of array adapter doesn't change !!!!
         * always implements getCount() to prevent problem !!!!
         */
    	super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        //this.data = data;
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
        
        Order order = father.orders.get(position);
        holder.imgIcon.setImageResource(R.drawable.freeorder);
        holder.txtTitle.setText("Table " + order.getTable() + " is waiting");
        
        return row;
    }
    
    
    /** ALWAYS SHOULD USE TO IMPLEMENT THIS */
    @Override
	public int getCount() {
		return father.orders.size();
	}

	static class OrderHolder
    {
        ImageView imgIcon;    
        TextView txtTitle;
    }
}