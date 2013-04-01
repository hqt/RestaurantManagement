package com.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.helper.JSONParser;
import com.view.MainActivity;

public class Order {
	
	public static final String ORDER_ID = "id";
	public static final String ORDER_DATE = "date_create";
	public static final String ORDER_TABLE = "table";
	public static final String ORDER_DISH = "dishes";
	public static final String ORDER_STATUS = "status";   // wrong here
	public static final String ORDER_PRICE = "price";
	public static final String ORDER_CURRENCY = "currency";
	
    public static List<Order> loadServerOrders(String url) {
    	List<Order> orders = new ArrayList<Order>();
    	 
        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
 
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
                 int id = c.getInt(Order.ORDER_ID);
                 String date = c.getString(Order.ORDER_DATE);
                 int table = c.getInt(Order.ORDER_TABLE);
                 String dishes = c.getString(Order.ORDER_DISH);
                 String status = c.getString(Order.ORDER_STATUS);
                 Double price = c.getDouble(Order.ORDER_PRICE);
                 String currency = c.getString(Order.ORDER_CURRENCY);

                 Order order = new Order(id, date, table, dishes, status, price, currency);
                 orders.add(order);
             }
         } catch (JSONException e) {
             e.printStackTrace();
         }
    	 
    	 return orders;
    }
    
   

	int id;
	String date;
	int table;
	String dishes;
	String status;
	double price;
	String currency;
	public Order(int id, String date, int table, String dishes, String status,
			double price, String currency) {
		super();
		this.id = id;
		this.date = date;
		this.table = table;
		this.dishes = dishes;
		this.status = status;
		this.price = price;
		this.currency = currency;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTable() {
		return table;
	}
	public void setTable(int table) {
		this.table = table;
	}
	public String getDishes() {
		return dishes;
	}
	public void setDishes(String dishes) {
		this.dishes = dishes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	
	
}
