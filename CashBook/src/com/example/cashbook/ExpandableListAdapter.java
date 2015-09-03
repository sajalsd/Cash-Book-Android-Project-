package com.example.cashbook;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ExpandableListAdapter extends ArrayAdapter<Transaction> {
	

	Activity con;
	ArrayList<Transaction> allTransactions;
	
	
	public ExpandableListAdapter(Context context, ArrayList<Transaction> tr) {
		super(context, R.layout.list_tr, tr);
		this.con = (Activity) context;
		this.allTransactions = tr;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = null;
		
		if(convertView == null)
		{
			LayoutInflater inflater = con.getLayoutInflater();
			v = inflater.inflate(R.layout.list_tr, null);
			
			TextView date = (TextView) v.findViewById(R.id.textView1);
			TextView amount = (TextView) v.findViewById(R.id.textView2);
			TextView acType = (TextView) v.findViewById(R.id.textView3);
			
			Transaction alltr = allTransactions.get(position);
			
			date.setText(alltr.getTrDate());
			amount.setText(alltr.getTrAmount());
			acType.setText(alltr.getTrType());
			
		}else
		{
			v = convertView;
		}
		
		return v;
	}
	
	
	
}
