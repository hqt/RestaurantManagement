package com.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dish {
	
	private String name;
	private String description;
	private double cost;
	private String tag;
	private List<String> tags;

	public Dish(String name, String description, double cost, String tag) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.tag = tag;
		tags = new ArrayList<String>();
		tags = Arrays.asList(tag.split(","));
	}
	
	public String getName() { return name; }
	public String getDescription() { return description; }
	public double getCost() { return cost; }
}
