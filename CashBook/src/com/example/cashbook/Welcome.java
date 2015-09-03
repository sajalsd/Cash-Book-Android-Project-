package com.example.cashbook;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;


public class Welcome extends ActionBarActivity {

	private static final int TIMER_RUNTIME = 5000;
	Button btWelcome;
	DatabaseHelper dbHelper;
	ProgressBar mProgressBar;
	boolean mbActive;
	@Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);
//        btWelcome = (Button) findViewById(R.id.btWelcome);
//        dbHelper = new DatabaseHelper(this);
//        btWelcome.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//						    	
//		    	if(dbHelper.getUser() > 0){
//		    		Intent intent = new Intent(Welcome.this,LoginActivity.class);
//		    		startActivity(intent);
//		    	}
//		    	else
//		    	{
//		    		Intent intent = new Intent(Welcome.this,CreateUserActivity.class);
//		    		startActivity(intent);
//		    	}
//				
//			}
//		});
//    }
//    
	public void onCreate(final Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_welcome);
	    mProgressBar = (ProgressBar)findViewById(R.id.adprogress_progressBar);
	    dbHelper = new DatabaseHelper(this);
	    final Thread timerThread = new Thread() {
	        @Override
	            public void run() {
	            mbActive = true;
	            try {
	                int waited = 0;
	                    while(mbActive && (waited < TIMER_RUNTIME)) {
	                        sleep(200);
	                        if(mbActive) {
	                            waited += 200;
	                            updateProgress(waited);
	                        }
	                    }
	            } catch(InterruptedException e) {
	        } finally {
	                onContinue();
	        }
	      }
	    };
	    timerThread.start();
	}
	@Override
	public void onDestroy() {
	    super.onDestroy();
	}
	public void updateProgress(final int timePassed) {
	    if(null != mProgressBar) {
	        final int progress = mProgressBar.getMax() * timePassed / TIMER_RUNTIME;
	        mProgressBar.setProgress(progress);
	    }
	}

	public void onContinue() {
		if(dbHelper.getUser() > 0){
    		Intent intent = new Intent(Welcome.this,LoginActivity.class);
    		startActivity(intent);
    	}
    	else
    	{
    		Intent intent = new Intent(Welcome.this,CreateUserActivity.class);
    		startActivity(intent);
    	}
	    }

    
}
