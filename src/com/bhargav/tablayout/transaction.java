package com.bhargav.tablayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class transaction extends Activity {
	//private RadioButton radioitem;
	public RadioGroup MyRadioGroup;
	public ArrayList<String> FriendList = new ArrayList<String>();
	public SQLiteDatabase db;
	public Button Done, Option;
	public String iden = "lend";
	public EditText et_Amount, et_Desc;
	public ArrayList<String> tickedFriend = new ArrayList<String>();
	public TextView tv_ToFrom;
	public CheckBox cb;

	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transaction);
		
		Option = (Button)findViewById(R.id.Option);
		Option.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				yahoo();
			}

		});
		
		Done = (Button)findViewById(R.id.Done);
		Done.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DoneDone();
			}

		});

		traverseSummaryDatabase();

		addListenerOnButton();


	}

	private void traverseSummaryDatabase(){	
		ArrayList<SummaryContact> contactList = new ArrayList<SummaryContact>();
		SummaryDatabaseHandler db7 = new SummaryDatabaseHandler(this);
		contactList = db7.getAllContacts();
		for (SummaryContact s : contactList)
		{

			FriendList.add(s.getName());  //adding to arraylist
		}

		//updating list visible on activity
		int Array_Count=0;
		Array_Count = FriendList.size();
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.LinearLayoutFriends);
		my_layout.removeAllViews();
		for (int i = 0; i < Array_Count; i++) 
		{
			TableRow row = new TableRow(this);
			row.setId(i);
			row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
			CheckBox checkBox = new CheckBox(this);
			checkBox.setOnCheckedChangeListener(null);
			checkBox.setId(i);
			checkBox.setText(FriendList.get(i));
			row.addView(checkBox);  
			my_layout.addView(row);		
		}
	}

	private void DeleteFromArrayList(String abc){
		SummaryDatabaseHandler db6 = new SummaryDatabaseHandler(this);
		db6.deleteContact(abc);
		FriendList.remove(abc);
		int Array_Count=0;
		Array_Count = FriendList.size();
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.LinearLayoutFriends);
		my_layout.removeAllViews();
		for (int i = 0; i < Array_Count; i++) 
		{
			TableRow row = new TableRow(this);
			row.setId(i);
			row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
			CheckBox checkBox = new CheckBox(this);
			checkBox.setOnCheckedChangeListener(null);
			checkBox.setId(i);
			checkBox.setText(FriendList.get(i));
			row.addView(checkBox);  
			my_layout.addView(row);

		}
	}

	private void AddToArrayList(String abc){

		FriendList.add(abc);  //adding to arraylist		
		SummaryDatabaseHandler db6 = new SummaryDatabaseHandler(this);
		db6.addContact(new SummaryContact(1,abc,"0"));

		//updating list visible on activity
		int Array_Count=0;
		Array_Count = FriendList.size();
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.LinearLayoutFriends);
		my_layout.removeAllViews();
		for (int i = 0; i < Array_Count; i++) 
		{
			TableRow row = new TableRow(this);
			row.setId(i);
			row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
			CheckBox checkBox = new CheckBox(this);
			checkBox.setOnCheckedChangeListener(null);
			checkBox.setId(i);
			checkBox.setText(FriendList.get(i));
			row.addView(checkBox);  
			my_layout.addView(row);

		}


	}

	private void DoneDone(){

		et_Amount = (EditText)findViewById(R.id.etAmount);
		et_Desc = (EditText)findViewById(R.id.etDescription);

		String Amount = et_Amount.getText().toString();
		if(Amount.equals("")){
			Amount = "0";
		}
		String Description = et_Desc.getText().toString();
		if(Description.equals("")){
			Description = "No Description";
		}

		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM-dd HH:mm");
		long time = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance(TimeZone.getDefault(),Locale.getDefault());
		cal.setTimeInMillis(time);
		String mydate = sdf1.format(cal.getTime());

		LinearLayout my_layout = (LinearLayout)findViewById(R.id.LinearLayoutFriends);

		if(iden.equals("lend"))
		{
			for (int i = 0; i < my_layout.getChildCount(); i++) {
				View v1 = my_layout.getChildAt(i);
				TableRow v2 = (TableRow)v1;
				View v3 = v2.getChildAt(0);
				CheckBox v4 = (CheckBox)v3;
				if(v4.isChecked()){
					DatabaseHandler db2 = new DatabaseHandler(this);	    			
					db2.addContact(new Contact(1,v4.getText().toString(),mydate,"To",Amount,Description));
					SummaryDatabaseHandler db3 = new SummaryDatabaseHandler(this);
					db3.add(v4.getText().toString(),Amount);
				}

			}

		}
		else
		{
			for (int i = 0; i < my_layout.getChildCount(); i++) {
				View v1 = my_layout.getChildAt(i);
				TableRow v2 = (TableRow)v1;
				View v3 = v2.getChildAt(0);
				CheckBox v4 = (CheckBox)v3;
				if(v4.isChecked()){
					DatabaseHandler db2 = new DatabaseHandler(this);	    			
					db2.addContact(new Contact(1,v4.getText().toString(),mydate,"From",Amount,Description));
					SummaryDatabaseHandler db3 = new SummaryDatabaseHandler(this);
					db3.add(v4.getText().toString(),"-"+Amount);
				}

			}
		}
		

		et_Amount.setText("");
		et_Desc.setText("");
		for (int i = 0; i < my_layout.getChildCount(); i++) {
			View v1 = my_layout.getChildAt(i);
			TableRow v2 = (TableRow)v1;
			View v3 = v2.getChildAt(0);
			CheckBox v4 = (CheckBox)v3;
			v4.setChecked(false);

		}

	}

	private void addListenerOnButton() {

		MyRadioGroup = (RadioGroup)findViewById(R.id.radioGroup);
		//MyRadioGroup.clearCheck();

		MyRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup arg0, int id) {
				switch (id) {

				case R.id.radioLend:
					//int selectedId = MyRadioGroup.getCheckedRadioButtonId();
					//radioitem = (RadioButton) findViewById(selectedId);
					tv_ToFrom = (TextView)findViewById(R.id.tvToFrom);
					tv_ToFrom.setText("To");
					iden = "lend";
					break;
				case R.id.radioBorrow:
					tv_ToFrom = (TextView)findViewById(R.id.tvToFrom);
					tv_ToFrom.setText("From");
					iden = "borrow";
					break;
				}
			}
		});

	}
	
	public void yahoo()
	{
		CharSequence colors[] = new CharSequence[] {"Add Friend", "Delete Friend", "Quit"};
		AlertDialog.Builder builder = new AlertDialog.Builder(transaction.this);
		builder.setTitle("Options");
		builder.setItems(colors, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch(which)
				{
				case 0:							
					Builder builder = new Builder(transaction.this);
					final EditText input = new EditText(transaction.this);
					builder
					.setTitle("Add")
					.setMessage("My new Friend is : ")
					.setView(input)
					.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							String value = input.getText().toString();
							if (input.getText().toString().trim().length() == 0) {
								Context context = getApplicationContext();
								Toast.makeText(context,"Enter Input", Toast.LENGTH_SHORT).show();
							} else {
								AddToArrayList(value);
							}
							InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
							imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
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
					LinearLayout my_layout = (LinearLayout)findViewById(R.id.LinearLayoutFriends);
					boolean itsnull = true;
					for (int i = 0; i < my_layout.getChildCount(); i++) {
						View v1 = my_layout.getChildAt(i);
						TableRow v2 = (TableRow)v1;
						View v3 = v2.getChildAt(0);
						CheckBox v4 = (CheckBox)v3;
						if(v4.isChecked()){
							DeleteFromArrayList(v4.getText().toString());
							itsnull = false;
						}
					}
					if(itsnull){
						Context context = getApplicationContext();
						Toast.makeText(context,"First select friends to delete and then click delete friend !!", Toast.LENGTH_SHORT).show();
					}
					break;
				case 2:
					finish();
					break;
					
				default:
					break;

				}
			}
		});
		builder.show();
	}

}
