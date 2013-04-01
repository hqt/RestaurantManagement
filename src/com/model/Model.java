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
		url = MainActivity.server + "/" + "dishes.json";
	}
	
	// url to make request
	private static String url = "";
	 
	// JSON DISH NODE NAME
	private static final String TAG_ID = "DishID";
	private static final String TAG_NAME = "DishName";
	private static final String TAG_DESCRIPTION = "Description";
	private static final String TAG_PRICE = "Price";
	private static final String TAG_DISH_IMAGE = "DishImage";
	private static final String TAG_CURRENCY = "Currency";
	private static final String TAG_TAG = "Tag";
	private static final String TAG_DISCOUNT = "Discount";
	
	// JSON TABLE NODE NAME
	private static final String TABLE_ID = "id";
	private static final String TABLE_NO = "no";
	private static final String TABLE_STATUS = "status";
	
	ArrayList<HashMap<String, String>> category;
	public List<Dish> food;
	public List<Table> tables;
	
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
        String sample = jParser.getJSONFromUrl(url);
        Log.i("debug", sample);
        
        /** using read from file for testing pupose */
        // readFromFile();
        
        // contacts JSONArray
    	JSONArray dishes = null;
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
	
	public void parsingJSONTable() {       

        tables = new ArrayList<Table>();
 
        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
 
        String url = MainActivity.server + "/" + "tables.json";
        String sample = jParser.getJSONFromUrl(url);
        Log.i("debug", sample);
        
        JSONArray jsonArrayTable = null;
    	 try {       
 			
             // Getting Array of Foods 
    		 jsonArrayTable = new JSONArray(sample);
             
             // looping through All Contacts
             for(int i = 0; i < jsonArrayTable.length(); i++){
                 JSONObject c = jsonArrayTable.getJSONObject(i);
  
                 // Storing each json item in variable
                 int id = c.getInt(TABLE_ID);
                 int no = c.getInt(TABLE_NO);
                 String status = c.getString(TABLE_STATUS);

                 Table table = new Table(id, no, status);
                 tables.add(table);
                 
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
	
	public List<Table> getTables() { return tables; }
}
