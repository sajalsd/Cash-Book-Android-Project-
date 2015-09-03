package com.example.cashbook;

import java.util.ArrayList;
import android.widget.AdapterView.OnItemClickListener;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AcountsActivity extends ActionBarActivity {
	
	
	
	static ListView lvList;
	TextView tvShowAll;
	CustomizedAdapter adapter;
	DatabaseHelper dbHelper;
	Spinner spnAcType;
	public static String[] acTypeOption = { "Show All", "Receivable", "Payable" };
	ArrayAdapter<String> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acounts);
		spnAcType = (Spinner) findViewById(R.id.spinnerAcType);
		arrayAdapter = new ArrayAdapter<String>(AcountsActivity.this,
				android.R.layout.simple_spinner_item, acTypeOption);
		spnAcType.setAdapter(arrayAdapter);

		lvList = (ListView) findViewById(R.id.listView1);

		dbHelper = new DatabaseHelper(AcountsActivity.this);
		ArrayList<Account> ac = dbHelper.getAllAccount("Show All");
		adapter = new CustomizedAdapter(AcountsActivity.this, ac);
		lvList.setAdapter(adapter);
		
		spnAcType.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
            	
            	String s = spnAcType.getItemAtPosition(arg2).toString();
        		ArrayList<Account> ac = dbHelper.getAllAccount(s);
       
                if (ac != null && ac.size() > 0) {

        				adapter = new CustomizedAdapter(AcountsActivity.this, ac);
        				lvList.setAdapter(adapter);
                }
        		else
        		{
        			Toast.makeText(
            				getApplicationContext(),
            				"Empty List!!!",
            				Toast.LENGTH_LONG).show();
        		}
        		
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

			
        });
		
		
		
		
		
		lvList.setOnItemClickListener(new OnItemClickListener() {
			
			  public void onItemClick(AdapterView<?> myAdapter, View myView, int pos, long mylng) {
				String contactName = ((TextView) myView.findViewById(R.id.textView1)).getText().toString();
				String contactPhone = ((TextView) myView.findViewById(R.id.textView2)).getText().toString();
				String contactACTYPE = ((TextView) myView.findViewById(R.id.textView3)).getText().toString();
				Intent intent = new Intent(AcountsActivity.this, AcountDetailsActivity.class);
			    intent.putExtra("message_name", contactName);
			    intent.putExtra("message_phone", contactPhone);
			    intent.putExtra("message_acType", contactACTYPE);
			    startActivity(intent);
			    finish();

			  }                 
			});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acounts, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.addAc:
			startActivity(new Intent(AcountsActivity.this,
					ManageAcountActivity.class));
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}
}
