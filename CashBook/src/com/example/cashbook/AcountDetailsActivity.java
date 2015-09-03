package com.example.cashbook;

import java.util.ArrayList;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AcountDetailsActivity extends ActionBarActivity {

	DatabaseHelper dbHelper;
	ExpandableListAdapter adapter;
	TextView name, phone, acType, totalAmount;
	ListView lvalltr;
	Button btAddTr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acount_details);
		
		final String Contact_acName = getIntent().getStringExtra("message_name");
		final String Contact_acPhone = getIntent().getStringExtra("message_phone");
		//String Contact_acType = getIntent().getStringExtra("message_acType");
		
		dbHelper = new DatabaseHelper(this);
		
		name = (TextView) findViewById(R.id.tvdetailsName);
		phone = (TextView) findViewById(R.id.tvdetailsPhone);
		acType = (TextView) findViewById(R.id.tvdetailsAccountType);
		totalAmount = (TextView) findViewById(R.id.tvdetailsAmount);
		
	
		Transaction  toalAmount  = dbHelper.getTotalTransactioninfo(Contact_acName); 
		totalAmount.setText("Total"+toalAmount.getTrType()+"   "+toalAmount.getTrAmount());
		acType.setText(toalAmount.getTrType());
		
		lvalltr = (ListView) findViewById(R.id.listView1);
		btAddTr = (Button)findViewById(R.id.btdetailsAddTr);
		btAddTr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AcountDetailsActivity.this,AddTransactionActivity.class );
				intent.putExtra("message_name",Contact_acName );
			    intent.putExtra("message_phone", Contact_acPhone);
			    startActivity(intent);
				
			}
		});
		
		name.setText("NAME: "+Contact_acName);
		phone.setText("PHONE NO." +Contact_acPhone);
		
		ArrayList<Transaction> tr = dbHelper.getAllTransaction(Contact_acName);
		Log.e("Number of Transaction found",String.valueOf(tr.size()));

		if (tr != null && tr.size() > 0) {

			adapter = new ExpandableListAdapter(AcountDetailsActivity.this,tr);
			lvalltr.setAdapter(adapter);
			
		} else {
			Toast.makeText(getApplicationContext(), "Empty List!!!",
					Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acount_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
