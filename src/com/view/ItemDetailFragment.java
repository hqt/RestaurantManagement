package com.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.model.MenuLeftList;
import com.model.MenuLeftList.Category;

/**
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link MainActivity} in two-pane mode (on tablets) or a
 * {@link ItemDetailActivity} on handsets.
 */
public class ItemDetailFragment extends Fragment {
	/** The fragment argument representing the item ID that this fragment represents.*/
	public static final String ARG_ITEM_ID = "item_id";

	/** The dummy content this fragment is presenting. */
	private Category mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the fragment
	 * (e.g. upon screen orientation changes).
	 */
	public ItemDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * this fragment will be called when user click on left panel
		 * --> always has argument : item_id (ARG_ITEM_ID) ==> id of list
		 * find which string this item_id maps to => find location of this category in the list
		 */
		if (getArguments().containsKey(ARG_ITEM_ID)) {
			/* Load the content specified by the fragment arguments.
			   In a real-world scenario, use a Loader to load content from a content provider.
			 */
			//mItem = DummyDataModel.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
			mItem = MenuLeftList.CATEGORIES_MAP.get(getArguments().getString(ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_item_detail,	container, false);

		// Show the dummy content as text in a TextView at right panel
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
		}

		return rootView;
	}
}
