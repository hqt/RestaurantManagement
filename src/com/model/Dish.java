package com.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dish {
	
	private int id;
	private String name;
	private String description;
	private double price;
	private double discount;
	private String currency;
	private String tag;
	private String image;
	private List<String> tags;
	
	
	
	public Dish(int id, String name, String description, double price, double discount,
			String currency, String tag, String image) {
		
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.currency = currency;
		this.tag = tag;
		this.image = image;
		
		tags = new ArrayList<String>();
		tags = Arrays.asList(tag.split(","));
	}
	
	public boolean findTag(String text) {
		for (int i = 0; i < tags.size(); i++) {
			if (text.equalsIgnoreCase(tags.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}

