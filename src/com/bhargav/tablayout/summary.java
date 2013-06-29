package com.bhargav.tablayout;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class summary extends Activity {
	ListView recordlist;
	ArrayList<SummaryContact> details;
	String query = "SELECT  * FROM SummaryContacts";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary);
		recordlist = (ListView) findViewById(R.id.listData);
		details = new ArrayList<SummaryContact>();
		SummaryDatabaseHandler db7 = new SummaryDatabaseHandler(this);
		details = db7.getresultforquery(query);
		recordlist.setAdapter(new SummaryCustomAdapter(details , this));

		recordlist.setOnItemClickListener(new OnItemClickListener() {
			@Override           	
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				CharSequence colors[] = new CharSequence[] {"Edit", "Clear", "Show Detail"};
				AlertDialog.Builder builder = new AlertDialog.Builder(summary.this);
				builder.setTitle("Options");
				builder.setItems(colors, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch(which)
						{
						case 0:							
							Builder builder = new Builder(summary.this);
							final EditText input = new EditText(summary.this);
							builder
							.setTitle("Edit")
							.setMessage("Set new value to : ")
							.setView(input)
							.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog, int which) {
									String value = input.getText().toString();
									if(!isInteger(value)){
										Context context = getApplicationContext();
										Toast.makeText(context,"You should enter integer", Toast.LENGTH_SHORT).show();
									}
									else{
										if (input.getText().toString().trim().length() == 0) {
											Context context = getApplicationContext();
											Toast.makeText(context,"You didn't enter anything !", Toast.LENGTH_SHORT).show();
										} else {
											SummaryDatabaseHandler db8 = new SummaryDatabaseHandler(summary.this);
											db8.add(details.get(position).getName(),String.valueOf((-1)*Integer.parseInt(details.get(position).getAmount())+Integer.parseInt(value)));
											onCreate(null);
										}
										InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
										imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
									}

								}
							})
							.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog, int which) {
									InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
									imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
								}

							});

							builder.show();
							input.requestFocus();
							InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
							imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

							break;
						case 1:
							SummaryDatabaseHandler db9 = new SummaryDatabaseHandler(summary.this);
							db9.ClearOne(details.get(position).getID());
							onCreate(null);
							break;
						case 2:
							Intent detailsIntent = new Intent(summary.this, details.class);
							detailsIntent.putExtra("FilterFriend",details.get(position).getName());
							startActivity(detailsIntent);
							break;

						default:
							break;

						}
					}
				});
				builder.show();
			}
		}); 

	}

	public static boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		}
		// only got here if we didn't return false
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		onCreate(null);
	}

}