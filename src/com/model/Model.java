package com.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.AssetManager;
import android.util.Log;

import com.helper.JSONParser;
import com.view.MainActivity;

public class Model {
	
	MainActivity activity;
	public Model(MainActivity activity) {
		this.activity = activity;
	}
	
	// url to make request
	private static String url = "http://10.0.2.2:3000/dishes.json";
	 
	// JSON Node names
	private static final String TAG_ID = "DishID";
	private static final String TAG_NAME = "DishName";
	private static final String TAG_DESCRIPTION = "Description";
	private static final String TAG_PRICE = "Price";
	private static final String TAG_DISH_IMAGE = "DishImage";
	private static final String TAG_CURRENCY = "Currency";
	private static final String TAG_TAG = "Tag";
	private static final String TAG_DISCOUNT = "Discount";
	
	// contacts JSONArray
	JSONArray dishes = null;
	ArrayList<HashMap<String, String>> category;
	List<Dish> food;
	String sample = "";
	
	/**
	 * parse real json
	 */
	public void parsingJSONFood() {       

		// Hashmap for ListView
        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
        
        // ArrayList food
        food = new ArrayList<Dish>();
 
        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
 
        /**
         * getting json from url    
         * this networking code is hard and done by JSONParse class
         */
        // JSONObject json = jParser.getJSONFromUrl(url);
        //String sample = jParser.getJSONFromUrl(url);
        
        /** using read from file for testing pupose */
        readFromFile();
        
		try {       
			
            // Getting Array of Foods 
            dishes = new JSONArray(sample);
            
            // looping through All Contacts
            for(int i = 0; i < dishes.length(); i++){
                JSONObject c = dishes.getJSONObject(i);
 
                // Storing each json item in variable
                int id = c.getInt(TAG_ID);
                String name = c.getString(TAG_NAME);
                String description = c.getString(TAG_DESCRIPTION);
                double price = c.getDouble(TAG_PRICE);
                String dish_image = c.getString(TAG_DISH_IMAGE);
                String currency = c.getString(TAG_CURRENCY);
                String tag = c.getString(TAG_TAG);
                double discount = c.getDouble(TAG_DISCOUNT);
                
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();
 
                // adding each child node to HashMap key => value
                map.put(TAG_ID, id + "");
                map.put(TAG_NAME, name);
                map.put(TAG_DESCRIPTION, description);
                map.put(TAG_PRICE, price + "");
                map.put(TAG_DISH_IMAGE, dish_image);
                map.put(TAG_CURRENCY, currency);
                map.put(TAG_TAG, tag);
                map.put(TAG_DISCOUNT, discount + "");
                
                // adding HashList to ArrayList
                contactList.add(map);
                Dish dish = new Dish(id, name, description, price,
                			discount, currency, tag, dish_image);
                food.add(dish);
                
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
	}
	
	public void readFromFile() {
		AssetManager am = activity.getAssets();
		try {
			InputStream is = am.open("sample.json");
			Scanner scanner = new Scanner(is);
			sample = scanner.nextLine();
			
			Log.d("Debug", sample);
			
		} catch (IOException e) {
			Log.i("error", "Cannot read file");
		}
	}
	
	public List<Dish> getDishes() { return food; }

}
