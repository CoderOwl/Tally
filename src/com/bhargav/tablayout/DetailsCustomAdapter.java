package com.bhargav.tablayout;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DetailsCustomAdapter extends BaseAdapter {

	private ArrayList<Contact> _data;
	Context _c;

	DetailsCustomAdapter (ArrayList<Contact> data, Context c){
		_data = data;
		_c = c;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return _data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _data.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.single_row_item, null);
		}

		TextView textViewfriend = (TextView) v.findViewById(R.id.tv_friend);
		TextView textViewAmount = (TextView) v.findViewById(R.id.tv_amount);
		TextView textViewDesc = (TextView) v.findViewById(R.id.tv_description);        
		TextView textViewDate = (TextView) v.findViewById(R.id.tv_date);

		Contact Trans = _data.get(position);
		String toFrom = Trans.getToFrom();
		if(toFrom.equals("To")){
			textViewfriend.setText("me -> " + Trans.getName()); 
		}
		else{
			
			textViewfriend.setText(Trans.getName() + " -> me"); 
		}
		                       
		textViewAmount.setText("Rs. "+Trans.getAmount());
		textViewDesc.setText(Trans.getDescription());
		textViewDate.setText(Trans.getDate());


		return v;
	}
}