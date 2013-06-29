package com.bhargav.tablayout;

public class Contact {
	
	//private variables
	int _id;
	String _name;
	String _date;
	String _ToFrom;
	String _amount;
	String _description;
	
	// Empty constructor
	public Contact(){
		
	}
	// constructor
	public Contact(int id, String name, String date, String ToFrom, String amount, String description){
		this._id = id;
		this._name = name;
		this._date = date;
		this._ToFrom = ToFrom;
		this._amount = amount;
		this._description = description;
		
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
	
	// getting phone number
	public String getDate(){
		return this._date;
	}
	
	// setting phone number
	public void setDate(String Date){
		this._date= Date;
	}
	
	public String getToFrom(){
		return this._ToFrom;
	}
	
	// setting phone number
	public void setToFrom(String ToFrom){
		this._ToFrom = ToFrom ;
	}
	

	public String getAmount(){
		return this._amount;
	}
	
	// setting phone number
	public void setAmount(String a){
		this._amount = a;
	}
	
	public String getDescription(){
		return this._description;
	}
	
	// setting phone number
	public void setDescription(String a){
		this._description = a;
	}
	
	
}