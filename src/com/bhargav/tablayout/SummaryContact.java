package com.bhargav.tablayout;

public class SummaryContact {
	
	//private variables
	int _id;
	String _name;
	String _amount;
	
	// Empty constructor
	public SummaryContact(){
		
	}
	// constructor
	public SummaryContact(int id, String name, String amount){
		this._id = id;
		this._name = name;
		this._amount = amount;
		
	}
	
	// getting ID
	public int getID(){
		return this._id;
	}
	
	// setting id
	public void setID(int id){
		this._id = id;
	}
	
	// getting name
	public String getName(){
		return this._name;
	}
	
	// setting name
	public void setName(String name){
		this._name = name;
	}

	
	public String getAmount(){
		return this._amount;
	}
	
	// setting phone number
	public void setAmount(String a){
		this._amount = a;
	}
	
	
	
}