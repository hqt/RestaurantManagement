package com.view.waiter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.model.Table;
import com.view.MainActivity;
import com.view.R;

public class ImageAdapterTableWaiter extends ArrayAdapter<Table> {
	
	public static enum TABLE_STATUS {
		BUSY,
		FREE,
		DIRTY
	}
	
	private Context context;
	WaiterViewFragment father;
	MainActivity activity;
	int layoutResourceId;  

	public ImageAdapterTableWaiter(Context context, WaiterViewFragment father, int layoutResourceId, List<Table> data) {
		super(context, layoutResourceId, data);
		this.context = context;
		this.father = father;
		this.activity = (MainActivity) context;
		this.layoutResourceId = layoutResourceId;
	}
 
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		View grid = convertView;
		TableHolder holder = null;
		
		if (grid == null) {
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			grid = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new TableHolder();
			holder.imgIcon = (ImageView)grid.findViewById(R.id.grid_table_image);
			holder.txtTitle = (TextView)grid.findViewById(R.id.grid_table_status);
			
			grid.setTag(holder);
		}
		else {
			holder = (TableHolder)grid.getTag();
		}
		
		Table table = getTable(position);
		TABLE_STATUS choice = TABLE_STATUS.valueOf(table.getStatus().toUpperCase());
		if (choice == TABLE_STATUS.BUSY) {
			holder.imgIcon.setImageResource(R.drawable.actiontable);
			holder.txtTitle.setText("Table " + table.getNo() + ": busy");
		}
		else {
			holder.imgIcon.setImageResource(R.drawable.freetable);
			holder.txtTitle.setText("Table " + table.getNo() + ": not busy");
		}
		
		return grid;
	}
	
	/** always implement this */
	@Override
	public int getCount() {
		return getTables().size();
	}
 
	@Override
	public Table getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	public List<Table> getTables() { return activity.model.getTables(); }
	public Table getTable(int position) { return activity.model.getTables().get(position); }
	
	public static class TableHolder {
		 ImageView imgIcon;    
	     TextView txtTitle;
	}
}

