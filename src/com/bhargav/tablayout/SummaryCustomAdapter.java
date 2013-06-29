package com.bhargav.tablayout;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SummaryCustomAdapter extends BaseAdapter {

	private ArrayList<SummaryContact> _data;
	Context _c;

	SummaryCustomAdapter (ArrayList<SummaryContact> data, Context c){
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
			v = vi.inflate(R.layout.single_row_item_for_summary, null);
		}

		TextView textViewfriend = (TextView) v.findViewById(R.id.tv_friend);
		TextView textViewAmount = (TextView) v.findViewById(R.id.tv_amount);

		SummaryContact Trans = _data.get(position);
		textViewfriend.setText(Trans.getName());
		textViewAmount.setText("Rs. "+Trans.getAmount());


		return v;
	}
}