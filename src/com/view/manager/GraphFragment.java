package com.view.manager;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.androidplot.series.XYSeries;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;
import com.view.MainActivity;
import com.view.R;

public class GraphFragment extends Fragment {
	
	private static class APRIndexFormat extends Format {
		private static final long serialVersionUID = 1L;

		@Override
	       public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
	           Number num = (Number) obj;
	 
	           // using num.intValue() will floor the value, so we add 0.5 to round instead:
	           int roundNum = (int) (num.floatValue() + 0.5f);
	           switch(roundNum) {
	               case 0:
	                   toAppendTo.append("Azimuth");
	                   break;
	               case 1:
	                   toAppendTo.append("Pitch");
	                   break;
	               case 2:
	                   toAppendTo.append("Roll");
	                   break;
	               default:
	                   toAppendTo.append("Unknown");
	           }
	           return toAppendTo;
	       }

		@Override
		public Object parseObject(String string, ParsePosition position) {
			return null;
		}
	}

	/** MainActivity for reference */
	public MainActivity activity;
	
	private XYPlot xyPlot;

	
	final String[] mMonths = new String[] {
	        "Jan","Feb", "Mar","Apr", "May","Jun",
	        "Jul", "Aug","Sep","Oct", "Nov","Dec"
	    };
	
	
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
	    
	    @SuppressWarnings("deprecation")
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	    	View rootView = inflater.inflate(R.layout.graph_fragment, container, false);
	    	
	    	xyPlot = (XYPlot) (rootView).findViewById(R.id.mySimpleXYPlot);
	    	 
	        Number[] income =  {2000, 2500, 2700, 3000, 2800, 3500, 3700, 3800 };
	        Number[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400 };
	 
	        // Converting the above income array into XYSeries
	        XYSeries incomeSeries = new SimpleXYSeries(
	            Arrays.asList(income),                   // array => list
	            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY , // Y_VALS_ONLY means use the element index as the x value
	            "Income");                                  // Title of this series
	 
	        // Converting the above expense array into XYSeries
	        XYSeries expenseSeries = new SimpleXYSeries(
	            Arrays.asList(expense),                 // array => list
	            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
	            "Expense");                                // Title of this series
	 
	        // Create a formatter to format Line and Point of income series
	        LineAndPointFormatter incomeFormat = new LineAndPointFormatter(
	            Color.rgb(0, 0, 255),                   // line color
	            Color.rgb(200, 200, 200),               // point color
	            null );                                    // fill color (none)
	 
	        // Create a formatter to format Line and Point of expense series
	        LineAndPointFormatter expenseFormat = new LineAndPointFormatter(
	            Color.rgb(255, 0, 0),                   // line color
	            Color.rgb(200, 200, 200),               // point color
	            null);                                    // fill color (none)
	 
	        // add expense series to the xyplot:
	        xyPlot.addSeries(expenseSeries,expenseFormat);
	 
	        // add income series to the xyplot:
	        xyPlot.addSeries(incomeSeries, incomeFormat);
	 
	        // Formatting the Domain Values ( X-Axis )  
	        xyPlot.setDomainValueFormat(new Format() {
	 
	            @Override
	            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
	                return new StringBuffer( mMonths[ ( (Number)obj).intValue() ]  );
	            }
	 
	            @Override
	            public Object parseObject(String source, ParsePosition pos) {
	                return null;
	            }
	        });
	 
	        xyPlot.setDomainLabel("");
	        xyPlot.setRangeLabel("Amount in Dollars");
	 
	        // Increment X-Axis by 1 value
	        xyPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
	 
	        xyPlot.getGraphWidget().setRangeLabelWidth(50);
	 
	        // Reduce the number of range labels
	        xyPlot.setTicksPerRangeLabel(2);
	 
	        // Reduce the number of domain labels
	        xyPlot.setTicksPerDomainLabel(2);
	 
	        // Remove all the developer guides from the chart
	        xyPlot.disableAllMarkup();
	        
	        Button button = (Button) (rootView).findViewById(R.id.returnBtn);
	        button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ManagerFragment managerFragment = new ManagerFragment();
					activity.getSupportFragmentManager().beginTransaction().
					replace(R.id.item_detail_container, managerFragment).commit();
				}
			});
	        
			return rootView;
	    }

	    
		@Override
		public void onPause() {
			super.onPause();
		}

		
}
