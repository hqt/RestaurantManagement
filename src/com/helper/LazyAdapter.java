package com.helper;

import java.util.List;

import android.app.ListFragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.model.MenuLeftList.Category;
import com.view.ItemListFragment;
import com.view.R;
import com.view.R.drawable;

public class LazyAdapter extends BaseAdapter {
	
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
	
    
    private ItemListFragment activity;
    private List<Category> data;
    private static LayoutInflater inflater=null;
    
    public LazyAdapter(ItemListFragment a, List<Category> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row_layout, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        title.setText(data.get(position).toString());
        
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
        LeftMenuCategories choice = LeftMenuCategories.valueOf(data.get(position).id.toUpperCase());
        switch (choice) {
        	case MENU:
        		thumb_image.setImageResource(drawable.menu);
        		break;
        	case ORDER:
        		thumb_image.setImageResource(drawable.order);
        		break;
        	case WAITER:
        		thumb_image.setImageResource(drawable.waiter);
        		break;
        	case COOK:
        		thumb_image.setImageResource(drawable.cook);
        		break;
        	case MANAGER:
        		thumb_image.setImageResource(drawable.manager);
        		break;
        	case SETTING:
        		thumb_image.setImageResource(drawable.setting);
        		break;
        	case ABOUT:
        		thumb_image.setImageResource(drawable.about);
        		break;
        	case ACCEPT:
        }

        return vi;
    }
}