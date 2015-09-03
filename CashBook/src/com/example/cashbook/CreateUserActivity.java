package com.example.cashbook;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUserActivity extends ActionBarActivity {

	EditText etName, etPhone, etPassword;
	DatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_user);

		etName = (EditText) findViewById(R.id.etUserName);
		etPhone = (EditText) findViewById(R.id.etUserPhone);
		etPassword = (EditText) findViewById(R.id.etUserPassword);
		dbHelper = new DatabaseHelper(this);
	}

	public void saveUser(View v) {
		String userFullName = etName.getText().toString();
		String userPhone = etPhone.getText().toString();
		String userPassword = etPassword.getText().toString();
		String user_username = "admin";

		User user = new User(userFullName, userPhone, user_username , userPassword);

		Long inserted = dbHelper.insertUser(user);
		if (inserted >= 0) {

			Toast.makeText(getApplicationContext(),
					"New user Created..!!\n Program Starting Again....!!",
					Toast.LENGTH_LONG).show();
			Intent intent = new Intent(CreateUserActivity.this,
					Welcome.class);
			startActivity(intent);

		} else {
			Toast.makeText(getApplicationContext(), "Failed, Something goes Wrong..!!!\n Try Again",
					Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_user, menu);
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
