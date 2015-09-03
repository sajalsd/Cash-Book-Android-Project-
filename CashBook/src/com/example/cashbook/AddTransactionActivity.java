package com.example.cashbook;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddTransactionActivity extends ActionBarActivity {
	
	Button BtAddtoDB;
	EditText etTrDate,etTrAmount;
	Spinner spTrType;
	public static String[] trTypeOption = { "Receive", "Pay" };
	ArrayAdapter<String> arrayAdapter;
	
	DatabaseHelper dbHelper;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_transaction);
		etTrAmount  = (EditText) findViewById(R.id.etTrAmount);
		BtAddtoDB = (Button)findViewById(R.id.btAddtrDB);
		
		etTrDate  = (EditText) findViewById(R.id.etTrDate);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = df.format(c.getTime());
		etTrDate.setText(formattedDate);
		
		
		spTrType = (Spinner)findViewById(R.id.spTrType);
		arrayAdapter = new ArrayAdapter<String>(AddTransactionActivity.this,
				android.R.layout.simple_spinner_item, trTypeOption);
		spTrType.setAdapter(arrayAdapter);
		
		dbHelper = new DatabaseHelper(this);
		
		BtAddtoDB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String tr_name = getIntent().getStringExtra("message_name");
				String tr_phone = getIntent().getStringExtra("message_phone");
				String tr_amount = etTrAmount.getText().toString();
				String tr_Date = etTrDate.getText().toString();
				TextView textView = (TextView)spTrType.getSelectedView();
				String tr_type = textView.getText().toString();
				
				Transaction tr = new Transaction(tr_name, tr_Date, tr_amount,
						tr_type);

				Long inserted_tr = dbHelper.insertTransaction(tr);
				if ( inserted_tr >=0) {
					Intent intent = new Intent(AddTransactionActivity.this, AcountDetailsActivity.class);
					intent.putExtra("message_name", tr_name);
					intent.putExtra("message_phone", tr_phone);
					intent.putExtra("message_acType", tr_type);
					Toast.makeText(getApplicationContext(), "New Transaction added",
							Toast.LENGTH_LONG).show();
					startActivity(intent);
					finish();
				}
				
				
				
			}
		});
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_transaction, menu);
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
