package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuLeftList {
	
	public static List<Category> CATEGORIES = new ArrayList<Category>();

	public static Map<String, Category> CATEGORIES_MAP = new HashMap<String, Category>();

	static {
		// Add sample items.
		addCategory(new Category("menu", "Menu"));
		addCategory(new Category("order", "Order"));
		addCategory(new Category("waiter", "Waiter"));
		addCategory(new Category("cook", "Cook"));
		addCategory(new Category("manager", "Manager"));
		addCategory(new Category("about", "About"));
		addCategory(new Category("setting", "Setting"));
		addCategory(new Category("accept", "Accept"));
	}

	private static void addCategory(Category item) {
		CATEGORIES.add(item);
		CATEGORIES_MAP.put(item.id, item);
	}

	public static class Category {
		public String id;
		public String content;

		public Category(String id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
