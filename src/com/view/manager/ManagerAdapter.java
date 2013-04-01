package com.view.manager;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.view.R;

public class ManagerAdapter extends ArrayAdapter<String>{

    Context context; 
    int layoutResourceId;    
    ManagerFragment father;
    String[] data;
    
    public ManagerAdapter(Context context, ManagerFragment father, int layoutResourceId, String[] data) {
        /** problem here !!!!
         * data change, but data of array adapter doesn't change !!!!
         * always implements getCount() to prevent problem !!!!
         */
    	super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.father = father;
        this.data = data;
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
        
        switch (position) {
        	case 1:
        		 holder.imgIcon.setImageResource(R.drawable.graph);
        	     holder.txtTitle.setText("Restaurant Graph Statistic");
        	     break;
        	case 2:
	       		 holder.imgIcon.setImageResource(R.drawable.foodmanagement);
	       	     holder.txtTitle.setText("View all Restaurant dish");
	       	     break;
        	case 3:
	       		 holder.imgIcon.setImageResource(R.drawable.staffmanagement);
	       	     holder.txtTitle.setText("Staff Management");
	       	     break;
        	case 0:
	       		 holder.imgIcon.setImageResource(R.drawable.setting);
	       	     holder.txtTitle.setText("Order Management");
	       	     break;
        		
        }
        
        return row;
    }
    
    
    /** ALWAYS SHOULD USE TO IMPLEMENT THIS */
    @Override
	public int getCount() {
		return data.length;
	}

	static class OrderHolder
    {
        ImageView imgIcon;    
        TextView txtTitle;
    }
}