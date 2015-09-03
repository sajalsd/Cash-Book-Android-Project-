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
import android.widget.Toast;

public class ManageAcountActivity extends ActionBarActivity {

	EditText acName, acType, acPhone, acAmount, acDate;
	Button create;
	Spinner spnAcType;
	public static String[] acTypeOption = { "Receivable", "Payable" };
	ArrayAdapter<String> arrayAdapter;
	DatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_acount);
		acName = (EditText) findViewById(R.id.etAccountName);
		acPhone = (EditText) findViewById(R.id.etPhone);
		acAmount = (EditText) findViewById(R.id.etAmount);
		acDate = (EditText) findViewById(R.id.etDate);
		create = (Button) findViewById(R.id.btCreate);
		spnAcType = (Spinner) findViewById(R.id.spnAccountType);
		arrayAdapter = new ArrayAdapter<String>(ManageAcountActivity.this,
				android.R.layout.simple_spinner_dropdown_item, acTypeOption);
		spnAcType.setAdapter(arrayAdapter);
		dbHelper = new DatabaseHelper(this);

		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = df.format(c.getTime());
		acDate.setText(formattedDate);

		create.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String ac_name = acName.getText().toString();
				String ac_phone = acPhone.getText().toString();
				String ac_amount = acAmount.getText().toString();
				String ac_Date = acDate.getText().toString();
				String ac_type = spnAcType.getSelectedItem().toString();

				Account ac = new Account(ac_name, ac_phone, ac_type);
				Transaction tr = new Transaction(ac_name, ac_Date, ac_amount,
						ac_type);

				Long inserted_ac = dbHelper.insertAccount(ac);
				Long inserted_tr = dbHelper.insertTransaction(tr);
				if (inserted_ac >= 0 && inserted_tr >=0) {

					Intent intent = new Intent(ManageAcountActivity.this,
							AcountsActivity.class);
					startActivity(intent);
					Toast.makeText(getApplicationContext(), "New user Created",
							Toast.LENGTH_LONG).show();
							finish();

				} else {
					Toast.makeText(getApplicationContext(),
							"Failed..!!!\n Try Again", Toast.LENGTH_LONG)
							.show();
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_acount, menu);
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
