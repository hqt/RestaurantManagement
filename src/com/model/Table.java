package com.model;

public class Table {
	int id;
	int no;
	String status;
	
	public Table(int id, int no, String status) {
		this.id = id;
		this.no = no;
		this.status = status;
	}
	
	public int getNo() { return no; }
	public int getId() { return id; }
	public String getStatus() { return status; }
	
	public void setStatus(String status) { this.status = status; }

}
