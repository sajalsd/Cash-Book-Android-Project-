package com.example.cashbook;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	
	EditText etPassword;
	DatabaseHelper dbHelper;
	protected Object ActivityFlags;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		etPassword = (EditText) findViewById(R.id.etpassword);
		dbHelper = new DatabaseHelper(this);
		
	}
	public void login(View v) {
		String etPass = etPassword.getText().toString();
		String etPasswordDb = dbHelper.login_cheak();
		if (etPass.equals(etPasswordDb))
		{
			Toast.makeText(getApplicationContext(),
					"Success!!!!",
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(LoginActivity.this, AcountsActivity.class);
			startActivity(intent);
		}
		
		

	}
	 public void onBackPressed() {
	        //Display alert message when back button has been pressed
	        backButtonHandler();
	        return;
	    }
	 
	    public void backButtonHandler() {
	        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
	                LoginActivity.this);
	        // Setting Dialog Title
	        alertDialog.setTitle("Leave application?");
	        // Setting Dialog Message
	        alertDialog.setMessage("Are you sure you want to leave the application?");
	        // Setting Icon to Dialog
	        alertDialog.setIcon(R.drawable.dialog_icon);
	        // Setting Positive "Yes" Button
	        alertDialog.setPositiveButton("YES",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) {
	                    	Intent intent = new Intent(Intent.ACTION_MAIN);
	                    	intent.addCategory(Intent.CATEGORY_HOME);
	                    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	                    	startActivity(intent);
	                    }
	                });
	        // Setting Negative "NO" Button
	        alertDialog.setNegativeButton("NO",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) {
	                        // Write your code here to invoke NO event
	                        dialog.cancel();
	                    }
	                });
	        // Showing Alert Message
	        alertDialog.show();
	    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
