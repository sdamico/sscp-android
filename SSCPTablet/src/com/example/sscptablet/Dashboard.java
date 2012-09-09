package com.example.sscptablet;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Dashboard extends Activity {
		
	CanTCPTask canTCPTask;
	String ipAddress;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        Intent intent = getIntent();
        ipAddress = intent.getStringExtra(Settings.IP_ADDRESS);
        //canTCPTask = new CanTCPTask(this);
        //canTCPTask.execute(ipAddress);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_dashboard, menu);
        return true;
    }
    
    public void updateCanState(CanState canState)
    {
    	TextView textView = (TextView) findViewById(R.id.speedometer);

    	textView.setText(Double.toString(canState.getSpeed()));
    }
    
    protected void onPause()
    {
    	canTCPTask.cancel(true);
    	super.onPause();
    }
    
    protected void onResume()
    {
    	super.onResume();
        canTCPTask = new CanTCPTask(this);
    	canTCPTask.execute(ipAddress);
    }
    
    protected void onStop()
    {
    	canTCPTask.cancel(true);
    	super.onStop();
    }
}
