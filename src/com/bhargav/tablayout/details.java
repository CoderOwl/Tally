package com.bhargav.tablayout;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class details extends Activity {
	ListView recordlist;
	ArrayList<Contact> details;
	String query = "SELECT  * FROM contacts ORDER BY `id` DESC;";
	String FilterFriend = "phi";
	Bundle extras;
	Button DeleteAll;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		extras = getIntent().getExtras();
		if (extras != null) {
			FilterFriend = extras.getString("FilterFriend");	
		}
			
		setContentView(R.layout.details);
		recordlist = (ListView) findViewById(R.id.list_data);
		details = new ArrayList<Contact>();
		DatabaseHandler db7 = new DatabaseHandler(this);
		DeleteAll = (Button)findViewById(R.id.DeleteAll);
		DeleteAll.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(extras == null){
					AlertDialog.Builder builder = new AlertDialog.Builder(details.this);
					builder
					.setTitle("Erase All Transactions")
					.setMessage("Are you sure you want to delete all transactions ?")
					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {			      	
							//Yes button clicked, do something
							DatabaseHandler db11 = new DatabaseHandler(details.this);	    			
							db11.deleteAll();
							onCreate(null);
						}
					})
					.setNegativeButton("No", null)						//Do nothing on no
					.show();
				}
			}

		});
		
		if(extras != null){
			query = "SELECT  * FROM contacts WHERE name = '"+FilterFriend+"' ORDER BY `id` DESC;";
		}
		details = db7.getresultforquery(query);
		recordlist.setAdapter(new DetailsCustomAdapter(details , this));
		recordlist.setOnItemClickListener(new OnItemClickListener() {
			@Override           	
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				AlertDialog.Builder builder = new AlertDialog.Builder(details.this);
				builder
				.setTitle("Erase Transaction")
				.setMessage("Are you sure want to delete this transaction?")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {			      	
						//Yes button clicked, do something
					int TheID = details.get(position).getID();
						String friend = details.get(position).getName();
						String amount = details.get(position).getAmount();
						String ToFrom = details.get(position).getToFrom();
						if(ToFrom.equals("To")){
							SummaryDatabaseHandler db3 = new SummaryDatabaseHandler(details.this);
							db3.add(friend,"-"+amount);
						}
						else{
							SummaryDatabaseHandler db3 = new SummaryDatabaseHandler(details.this);
							db3.add(friend,amount);
						}

						DatabaseHandler db3 = new DatabaseHandler(details.this);
						Contact myContact = db3.getContact(TheID);
						db3.deleteContact(myContact);
						onCreate(null);

					}
				})
				.setNegativeButton("No", null)						//Do nothing on no
				.show(); 
				

			}
		});  
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		onCreate(null);
	}



}