package com.view.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.LineGraphView;
import com.view.MainActivity;
import com.view.R;

public class GraphFragment extends Fragment {

	/** MainActivity for reference */
	public MainActivity activity;
	
	
	/** empty constructor */
	public GraphFragment() {
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

	    	View rootView = inflater.inflate(R.layout.graph_fragment, container, false);
	    	
	    	LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.graph1);  
	    	
	    	
	    	// graph with dynamically genereated horizontal and vertical labels  
	    	Context context = null;
	    	GraphViewData[] data = new GraphViewData[] {  
		    	    new GraphViewData(1, 2.0d)  
		    	    , new GraphViewData(2, 1.5d)  
		    	    , new GraphViewData(2.5, 3.0d) // another frequency  
		    	    , new GraphViewData(3, 2.5d)  
		    	    , new GraphViewData(4, 1.0d)  
		    	    , new GraphViewData(5, 3.0d)  
		    	  };
	    	GraphView graphView = null;
	    	// graphView = new LineGraphView(context, title)
	    	layout.addView(graphView);  

			return rootView;
	    }

	    
		@Override
		public void onPause() {
			super.onPause();
		}

}
