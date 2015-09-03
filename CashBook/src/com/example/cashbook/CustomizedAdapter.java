package com.example.cashbook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomizedAdapter extends ArrayAdapter<Account> {
		
		Activity con;
		ArrayList<Account> allaccount;
		
		
		public CustomizedAdapter(Context context, ArrayList<Account> cas) {
			super(context, R.layout.list_item, cas);
			this.con = (Activity) context;
			this.allaccount = cas;

			
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View v =null;
			
			if(convertView ==null)
			{
				LayoutInflater inflater = con.getLayoutInflater();
				v = inflater.inflate(R.layout.list_item, null);
				
				TextView name = (TextView) v.findViewById(R.id.textView1);
				TextView phone = (TextView) v.findViewById(R.id.textView2);
				TextView acType = (TextView) v.findViewById(R.id.textView3);
				
				Account ac = allaccount.get(position);
				
				name.setText(ac.getAcName());
				phone.setText(ac.getAcPhone());
				acType.setText(ac.getAcType());
				
			}else
			{
				v = convertView;
			}
			
			return v;
		}
		
		
		
}
